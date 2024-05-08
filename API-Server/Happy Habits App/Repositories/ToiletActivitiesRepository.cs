using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class ToiletActivitiesRepository : IToiletActivitiesRepository
    {
        private readonly IMongoCollection<Toilet> _toiletActivitiesCollection;

        public ToiletActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _toiletActivitiesCollection = mongoDb.GetCollection<Toilet>(databaseSettings.Value.Collections["Toilet"]);
        }
        public async Task CreateToiletActivityAsync(Toilet toilet)
        {
            await _toiletActivitiesCollection.InsertOneAsync(toilet);
        }

        public async Task<List<Toilet>> GetAllToiletActivitiesAsync()
        {
            throw new NotImplementedException();
        }

        public async Task<Toilet> GetToiletActivityByUserAndIdAndDate(string userId, DateOnly date)
        {
            return await _toiletActivitiesCollection.Find<Toilet>(toilet => toilet.UserId == userId && toilet.Date.Equals(date)).FirstOrDefaultAsync();
        }

        public async Task UpdateToiletActivityAsync(Toilet toilet)
        {
            // Define a filter to locate the specific toilet habit based on its ID
            var filter = Builders<Toilet>.Filter.Eq(t => t.Id, toilet.Id);

            // Define the update operation
            var update = Builders<Toilet>.Update
                .Set(t => t.Date, toilet.Date)
                .Set(t => t.UserId, toilet.UserId)
                .Set(t => t.Type, toilet.Type)
                .Set(t => t.Hour, toilet.Hour)
                .Set(t => t.Note, toilet.Note);

            // Perform the update operation and get the updated document
            var options = new FindOneAndUpdateOptions<Toilet, Toilet>
            {
                ReturnDocument = ReturnDocument.After  // Returns the document after the update has been applied
            };

            // Execute the update operation
            await _toiletActivitiesCollection.FindOneAndUpdateAsync(filter, update, options);
        }
    }
}
