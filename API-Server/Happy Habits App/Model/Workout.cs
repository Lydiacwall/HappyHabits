using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public abstract class Workout(DateOnly date, string userId, string type, string time, string duration, string notes, string unitMeasurement) : Habit(date, userId)
    {
        [BsonElement("type")]
        public string Type { get; set; } = type;
        [BsonElement("time")]
        public string Time { get; set; } = time;
        [BsonElement("duration")]
        public string Duration { get; set; } = duration;
        [BsonElement("notes")]
        public string Notes { get; set; } = notes;
        [BsonElement("unitMeasurement")]
        public string UnitMeasurement { get; set; } = unitMeasurement;
    }
}
