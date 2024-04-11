using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App
{
    public class UserService
    {
        private readonly IMongoCollection<User> _users;
        public UserService(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _users = mongoDb.GetCollection<User>(databaseSettings.Value.CollectionName);
        }

        public async Task<List<User>> GetAsync() => await _users.Find(_ => true).ToListAsync();

    }
}
