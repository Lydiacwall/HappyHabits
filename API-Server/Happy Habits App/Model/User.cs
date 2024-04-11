using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class User
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; }

        [BsonElement("Username")]
        public string? Username { get; set; } = null;

        public string? Password { get; set; } = null;

        public string? Email { get; set; } = null;
    }
}
