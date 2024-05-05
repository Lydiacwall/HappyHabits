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
            _toiletActivitiesCollection = mongoDb.GetCollection<Toilet>(databaseSettings.Value.CollectionName);
        }
        public async Task<Toilet> CreateToiletActivityAsync(Toilet toilet)
        {
            await _toiletActivitiesCollection.InsertOneAsync(toilet);
            return toilet;
        }

        public async Task<List<Toilet>> GetAllToiletActivitiesAsync()
        {
            throw new NotImplementedException();
        }

        public async Task<Toilet> GetToiletActivityByUserAndIdAndDate(string userId, string habitId, DateOnly date)
        {
            return await _toiletActivitiesCollection.Find<Toilet>(toilet => toilet.UserId == userId && toilet.Date.Equals(date) && toilet.Id == habitId).FirstOrDefaultAsync();
        }

        public async Task<Toilet> UpdateToiletActivityAsync()
        {
            throw new NotImplementedException();
        }
    }
}
