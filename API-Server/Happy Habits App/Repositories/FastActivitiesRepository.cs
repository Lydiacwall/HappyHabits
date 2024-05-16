using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Happy_Habits_App.Repositories
{
    public class FastActivitiesRepository : IFastActivitiesRepository
    {
        private readonly IMongoCollection<FastActivity> _fastActivitiesCollection;

        public FastActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _fastActivitiesCollection = mongoDb.GetCollection<FastActivity>(databaseSettings.Value.Collections["FastActivity"]);
        }

        public async Task CreateFastActivityAsync(FastActivity fastActivity)
        {
            await _fastActivitiesCollection.InsertOneAsync(fastActivity);
        }

        public async Task DeleteFastActivityAsync(string userId, DateOnly date) => await _fastActivitiesCollection.DeleteOneAsync(fastActivity => fastActivity.UserId == userId && fastActivity.Date == date);

        public Task<List<FastActivity>> GetAllFastActivitiesAsync()
        {
            throw new NotImplementedException();
        }
    }
}
