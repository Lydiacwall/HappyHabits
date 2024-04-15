using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class User
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; }

        [BsonElement("name")]
        public string? Username { get; set; } = null;
        [BsonElement("password")]
        public string? Password { get; set; } = null;
        [BsonElement("email")]
        public string? Email { get; set; } = null;
    }
}
