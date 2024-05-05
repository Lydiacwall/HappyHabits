using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Weights(DateOnly date, string userId, string type, string time, string notes, string unitMeasurement, float? quantity, string muscleGroup, List<Exercise> exercises) : Workout(date, userId, type, time, notes, unitMeasurement, quantity)
    {
        [BsonElement("muscleGroup")]
        public string MuscleGroup { get; set; } = muscleGroup;
        [BsonElement("exercises")]
        public required List<Exercise> Exercises { get; set; } = exercises;
                
    }
}
