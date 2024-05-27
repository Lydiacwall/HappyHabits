using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class MoodActivitiesRepository : IMoodActivitiesRepository
    {
        private readonly IMongoCollection<Mood> _moodActivitiesCollection;

        public MoodActivitiesRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _moodActivitiesCollection = mongoDb.GetCollection<Mood>(databaseSettings.Value.Collections["Mood"]);
        }

        public async Task CreateMoodActivityAsync(Mood mood)
        {
            await _moodActivitiesCollection.InsertOneAsync(mood);
        }

        public async Task DeleteMoodHabitAsync(string userId, DateOnly date) => await _moodActivitiesCollection.DeleteOneAsync(mood => mood.UserId == userId && mood.Date == date);

        public async Task<List<Mood>> GetAllMoodActivitiesAsync()
        {
            throw new NotImplementedException();
        }

        public async Task<List<Mood>> GetMoodActivitiesByYearAsync(string userId, int year)
        {
            // Create a filter for the year
            var startOfYear = new DateOnly(year, 1, 1);
            var endOfYear = new DateOnly(year, 12, 31);

            var filter = Builders<Mood>.Filter.And(
                Builders<Mood>.Filter.Eq(m => m.UserId, userId),
                Builders<Mood>.Filter.Gte(m => m.Date, startOfYear),
                Builders<Mood>.Filter.Lte(m => m.Date, endOfYear)
            );

            // Find the documents matching the filter
            var result = await _moodActivitiesCollection.Find(filter).ToListAsync();

            return result;
        }
    }
}
