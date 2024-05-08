using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public abstract class Habit
    {
        [BsonId]
        [BsonRepresentation(MongoDB.Bson.BsonType.ObjectId)]
        public string? Id { get; set; } = null;

        [BsonElement("date")]
        public DateOnly Date { get; set; }

        [BsonElement("userId")]
        public string UserId { get; set; }

        protected Habit(DateOnly date, string userId)
        {
            Date = date;
            UserId = userId;
        }
    }
}
