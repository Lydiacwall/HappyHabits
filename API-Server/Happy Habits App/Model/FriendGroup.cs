using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class FriendGroup(string friend1Id, string friend2Id)
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; }
        [BsonElement("group")]
        public Tuple<string, string> Group { get; set; } = new Tuple<string, string>(friend1Id, friend2Id);
        [BsonElement("messages")]
        public Queue<Message> Messages { get; set; } = new Queue<Message>();
    }
}
