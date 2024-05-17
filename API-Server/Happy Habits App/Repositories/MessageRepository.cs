using Happy_Habits_App.Configurations;
using Happy_Habits_App.Model;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Happy_Habits_App.Repositories
{
    public class MessageRepository : IMessageRepository
    {
        private readonly IMongoCollection<FriendGroup> _friendGroupMongoCollection;

        public MessageRepository(IOptions<DatabaseSettings> databaseSettings)
        {
            var mongoClient = new MongoClient(databaseSettings.Value.ConnectionURI);
            var mongoDb = mongoClient.GetDatabase(databaseSettings.Value.DatabaseName);
            _friendGroupMongoCollection = mongoDb.GetCollection<FriendGroup>(databaseSettings.Value.Collections["FriendGroup"]);
        }

        public async Task<List<FriendGroup>> GetAllFriends(string userId)
        {
            var filter = Builders<FriendGroup>.Filter.Or(
                Builders<FriendGroup>.Filter.Eq("Group.Item1", userId),
                Builders<FriendGroup>.Filter.Eq("Group.Item2", userId)
            );

            var friendGroups = await _friendGroupMongoCollection.Find(filter).ToListAsync();

            return friendGroups ?? new List<FriendGroup>();
        }

        public async Task<Queue<Message>> GetAllMessagesByGroupId(string groupId)
        {
            var friendGroup = await _friendGroupMongoCollection.Find<FriendGroup>(group => group.Id == groupId).FirstOrDefaultAsync();

            if (friendGroup == null)
            {
                return new Queue<Message>();
            }
            return friendGroup.Messages;
        }

        public async Task<FriendGroup> GetFriendGroupById(string groupId)
        {
            return await _friendGroupMongoCollection.Find<FriendGroup>(group => group.Id == groupId).FirstOrDefaultAsync();
        }

        public async Task UpdateFriendGroupChat(FriendGroup group)
        {
            var filter = Builders<FriendGroup>.Filter.Eq(g => g.Id, group.Id);
            var updateResult = await _friendGroupMongoCollection.ReplaceOneAsync(filter, group);
        }
    }
}
