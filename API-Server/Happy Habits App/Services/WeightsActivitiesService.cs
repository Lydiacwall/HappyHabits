using Happy_Habits_App.Forms;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Model;
using Happy_Habits_App.Configurations;

namespace Happy_Habits_App.Services
{
    public class WeightsActivitiesService : IWeightsActivitiesService
    {
        private readonly IWeightsActivitiesRepository _weightsActivitiesRepository;
        public WeightsActivitiesService(IWeightsActivitiesRepository weightsActivitiesRepository)
        {
            _weightsActivitiesRepository = weightsActivitiesRepository;
        }

        public async Task AddWeightsActivity(WeightsForm form)
        {
            await _weightsActivitiesRepository.CreateWeightsActivity(
                new Weights(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Type,
                    form.Time,
                    form.Duration,
                    form.Notes,
                    "kg",
                    form.Exercises
                    ));
        }

        public async Task<WeightsStatistics> CalculateWeightsStatistics(string userId, int month, int year)
        {
            var workouts = await _weightsActivitiesRepository.GetWeightsActivitiesByUserAndDate(userId, month, year);

            if (workouts == null || workouts.Count == 0)
            {
                return new WeightsStatistics(0, new List<string>(), 0, 0, 0);
            }

            // Calculate total duration and total number of exercises
            int totalDuration = 0;
            Dictionary<string, int> exerciseCount = new Dictionary<string, int>();
            int totalExercises = 0;

            foreach (var workout in workouts)
            {
                totalDuration += MinuteCalculator.CalculateMinutes(workout.Duration);

                totalExercises += workout.Exercises.Count;

                foreach (var exercise in workout.Exercises)
                {
                    if (exerciseCount.ContainsKey(exercise.Name))
                    {
                        exerciseCount[exercise.Name]++;
                    }
                    else
                    {
                        exerciseCount[exercise.Name] = 1;
                    }
                }
            }

            // Calculate average duration
            double averageDuration = totalDuration / workouts.Count;
            Console.WriteLine($"Average duration (decimal): {averageDuration}"); // For debugging

            // Convert average duration to hours and minutes format
            int hours = (int)averageDuration; // Extract whole hours
            double fractionalPart = averageDuration - hours; // Extract fractional part
            int minutes = (int)(fractionalPart * 60); // Convert fractional part to minutes
            averageDuration = hours + minutes / 100.0; // Combine hours and minutes

            // Identify top 5 exercises
            var topExercises = exerciseCount.OrderByDescending(ec => ec.Value)
                                             .Take(5)
                                             .Select(ec => ec.Key)
                                             .ToList();

            // Calculate total number of workouts
            int totalWorkouts = workouts.Count;

            // Calculate average number of exercises per workout
            int averageExercisesPerWorkout = totalExercises / totalWorkouts;

            float avgKgsPerWorkout = CalculateAverageKgsPerWorkout(workouts);

            return new WeightsStatistics(averageDuration, topExercises, totalWorkouts, averageExercisesPerWorkout, avgKgsPerWorkout);
        }

        private float CalculateAverageKgsForSingleWorkout(Weights workout)
        {
            if (workout.Exercises == null || workout.Exercises.Count == 0)
            {
                return 0;
            }

            float totalKgs = workout.Exercises.Sum(e => e.Kgs);
            int totalExercises = workout.Exercises.Count;

            return totalKgs / totalExercises;
        }

        private float CalculateAverageKgsPerWorkout(List<Weights> weightsList)
        {
            if (weightsList == null || weightsList.Count == 0)
            {
                return 0;
            }

            float totalAverageKgs = weightsList.Sum(workout => CalculateAverageKgsForSingleWorkout(workout));
            int numberOfWorkouts = weightsList.Count;

            return totalAverageKgs / numberOfWorkouts;
        }
    }
}
