using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly IMongoCollection<User> _usersCollection;

        public UserRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _usersCollection = mongoDb.GetCollection<User>(databaseSettings.Value.CollectionName);
        }
        public async Task<IEnumerable<User>> GetAllUsers()
        {
            return await _usersCollection.Find(_ => true).ToListAsync();
        }

        public async Task<User> GetUserById(string userId)
        {
            var filter = Builders<User>.Filter.Eq(u => u.Id, userId);
            return await _usersCollection.Find(filter).FirstOrDefaultAsync();
        }

        public async Task CreateUser(User user)
        {
            await _usersCollection.InsertOneAsync(user);
        }

        public async Task UpdateUser(User user)
        {
            var filter = Builders<User>.Filter.Eq(u => u.Id, user.Id);
            await _usersCollection.ReplaceOneAsync(filter, user);
        }

        public async Task DeleteUser(string userId)
        {
            var filter = Builders<User>.Filter.Eq(u => u.Id, userId);
            await _usersCollection.DeleteOneAsync(filter);
        }
    }
}
