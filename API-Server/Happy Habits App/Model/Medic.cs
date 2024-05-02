namespace Happy_Habits_App.Model
{
    namespace Happy_Habits_App.Model
    {
        // Assuming the abstract Habit class is already defined somewhere in your project
        public class Medic(string date, string userId, string metric, string startDate, string endDate, float freqInQuantity, string freqInMetric) : Habit(date, userId)
        {
            public string Metric { get; set; } = metric;
            public string StartDate { get; set; } = startDate;
            public string EndDate { get; set; } = endDate;
            public float FrequencyInQuantity { get; set; } = freqInQuantity;
            public string FrequencyInMetric { get; set; } = freqInMetric;
        }
    }
}
