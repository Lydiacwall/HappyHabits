using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;

namespace Happy_Habits_App.Services
{
    public class UserService : IUserService
    {
        private readonly IMongoCollection<User> _usersCollection;
        public UserService(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _usersCollection = mongoDb.GetCollection<User>(databaseSettings.Value.CollectionName);
        }

        public async Task<List<User>> GetAsync() => await _usersCollection.Find(_ => true).ToListAsync();
        public async Task<User> GetByPasswordAndEmailAsync(string? password, string? email) => await _usersCollection.Find(x => x.Password == password && x.Email == email).FirstOrDefaultAsync();
        public async Task CreateAsync(User user) => await _usersCollection.InsertOneAsync(user);

        public async Task UpdateAsync(User user) => await _usersCollection.ReplaceOneAsync(x => x.Id == user.Id, user);

        public async Task RemoveAsync(string id) => await _usersCollection.DeleteOneAsync(x => x.Id == id);

    }
}
