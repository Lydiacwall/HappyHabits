using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public abstract class Habit(DateOnly date, string userId)
    {
        [BsonElement("id")]
        public string? Id { get; set; } = null;
        [BsonElement("date")]
        public required DateOnly Date { get; set; } = date;
        [BsonElement("userId")]
        public required string UserId { get; set; } = userId;
    }
}
