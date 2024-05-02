using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class FlexActivity(string date, string userId, string type, string time, string notes, string unitMeasurement, float? quantity, List<Exercise> exercises) : Workout(date, userId, type, time, notes, unitMeasurement, quantity)
    {
        [BsonElement("exercises")]
        public required List<Exercise> Exercises { get; set; } = exercises;
    }
}
