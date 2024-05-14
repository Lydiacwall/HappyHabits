using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class ExercisesWorkout : Workout
    {
        [BsonElement("exercises")]
        public List<string> Exercises { get; set; }
        public ExercisesWorkout(DateOnly date, string userId, string type, string time, string duration, string notes, string unitMeasurement, List<string> exercises)
            : base(date, userId, type, time, duration, notes, unitMeasurement)
        {
            Exercises = exercises;
        }
    }
}
