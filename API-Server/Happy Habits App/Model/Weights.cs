using System;
using System.Collections.Generic;
using MongoDB.Bson.Serialization.Attributes;

namespace Happy_Habits_App.Model
{
    public class Weights : Workout
    {
        [BsonElement("exercises")]
        public List<Exercise> Exercises { get; set; }

        public Weights(DateOnly date, string userId, string type, string time, string duration, string notes, string unitMeasurement, List<Exercise> exercises)
            : base(date, userId, type, time, duration, notes, unitMeasurement)
        {
            Exercises = exercises;
        }
    }
}
