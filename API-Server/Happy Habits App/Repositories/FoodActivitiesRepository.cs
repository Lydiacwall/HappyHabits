using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class FoodActivitiesRepository : IFoodActivitiesRepository
    {
        private readonly IMongoCollection<Food> _foodCollection;

        public FoodActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _foodCollection = mongoDb.GetCollection<Food>(databaseSettings.Value.Collections["Food"]);
        }

        public async Task CreateFoodActivityAsync(Food food)
        {
            await _foodCollection.InsertOneAsync(food);
        }

        public async Task DeleteFoodActivity(string id)
        {
            var filter = Builders<Food>.Filter.Eq(f => f.Id, id);
            await _foodCollection.DeleteOneAsync(filter);
        }

        public async Task<List<Food>> GetAllFoodActivitiesByUserByDate(string userId, DateOnly date)
        {
            var filter = Builders<Food>.Filter.And(
                Builders<Food>.Filter.Eq(f => f.UserId, userId),
                Builders<Food>.Filter.Eq(f => f.Date, date)
            );

            return await _foodCollection.Find(filter).ToListAsync();
        }
    }
}