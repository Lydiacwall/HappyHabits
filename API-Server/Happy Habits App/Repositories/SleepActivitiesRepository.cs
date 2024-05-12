using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class SleepActivitiesRepository : ISleepActivitiesRepository
    {
        private readonly IMongoCollection<Sleep> _sleepActivitiesCollection;

        public SleepActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _sleepActivitiesCollection = mongoDb.GetCollection<Sleep>(databaseSettings.Value.Collections["Sleep"]);
        }
        public async Task CreateSleepActivityAsybc(Sleep sleep)
        {
            await _sleepActivitiesCollection.InsertOneAsync(sleep);
        }

        public async Task DeleteSleepActivityAsync(string userId, DateOnly date) => await _sleepActivitiesCollection.DeleteOneAsync(sleep => sleep.UserId == userId && sleep.Date == date);


        public Task<List<Sleep>> GetAllSleepActivitiesAsync()
        {
            throw new NotImplementedException();
        }
    }
}
