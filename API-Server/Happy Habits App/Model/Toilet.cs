using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Toilet(DateOnly date, string userId, string type, string hour, string note) : Habit(date, userId)
    {
        [BsonElement("Type")]
        public string Type { get; set; } = type;
        [BsonElement("Note")]
        public string Note { get; set; } = note;
        [BsonElement("Hour")]
        public string Hour { get; set; } = hour;
    }
}
