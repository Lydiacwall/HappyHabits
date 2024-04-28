using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class User
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; }

        [BsonElement("firstName")]
        public string? FirstName { get; set; } = null;
        [BsonElement("lastName")]
        public string? LastName { get; set; } = null;
        [BsonElement("password")]
        public string? Password { get; set; } = null;
        [BsonElement("email")]
        public string? Email { get; set; } = null;
        [BsonElement("type")]
        public string? Type { get; set; } = null;
        [BsonElement("birthdate")]
        public string? Birthdate { get; set; } = null;
        [BsonElement("speciality")]
        public string? Speciality { get; set; } = "None";
    }
}
