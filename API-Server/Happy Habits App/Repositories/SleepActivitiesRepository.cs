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

        public async Task<List<Sleep>> GetSleepActivitiesByMonthAndUserAsync(int year, int month, string userId)
        {
            var startDate = new DateOnly(year, month, 1);
            var endDate = startDate.AddMonths(1);

            var filter = Builders<Sleep>.Filter.And(
                Builders<Sleep>.Filter.Gte(sleep => sleep.Date, startDate),
                Builders<Sleep>.Filter.Lt(sleep => sleep.Date, endDate),
                Builders<Sleep>.Filter.Eq(sleep => sleep.UserId, userId)
            );

            return await _sleepActivitiesCollection.Find(filter).ToListAsync();
        }
    }
}
