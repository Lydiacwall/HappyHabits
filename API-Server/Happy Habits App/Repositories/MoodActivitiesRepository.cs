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

        public async Task DeleteMoodHabit(string userId, DateOnly date) => await _moodActivitiesCollection.DeleteOneAsync(mood => mood.UserId == userId && mood.Date == date);

        public async Task<List<Mood>> GetAllMoodActivitiesAsync()
        {
            throw new NotImplementedException();
        }
    }
}
