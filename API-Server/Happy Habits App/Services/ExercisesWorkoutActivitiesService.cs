using Happy_Habits_App.Forms;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Model;
using Happy_Habits_App.Configurations;

namespace Happy_Habits_App.Services
{
    public class ExercisesWorkoutActivitiesService : IExercisesWorkoutActivitiesService
    {
        private readonly IExercisesWorkoutActivitiesRepository _exerciseWorkoutActivityRepository;

        public ExercisesWorkoutActivitiesService(IExercisesWorkoutActivitiesRepository exerciseWorkoutActivityRepository)
        {
            _exerciseWorkoutActivityRepository = exerciseWorkoutActivityRepository;
        }

        public async Task AddExerciseWorkoutActivity(ExercisesWorkoutForm form)
        {
            await _exerciseWorkoutActivityRepository.CreateExerciseWorkoutActivity(
                new ExercisesWorkout(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Type,
                    form.Time,
                    form.Duration,
                    form.Notes,
                    "",
                    form.Exercises));
        }

        public async Task<ExercisesWorkoutStatistics> GetWorkoutStatistics(string userId, int month, int year, string type)
        {
            var workouts = await _exerciseWorkoutActivityRepository.GetExercisesWorkoutActivitiesByUserAndDate(userId, month, year, type);
            Console.WriteLine($"Workouts retrieved: {workouts?.Count ?? 0}");

            if (workouts == null || workouts.Count == 0)
            {
                Console.WriteLine("No workouts found.");
                return new ExercisesWorkoutStatistics(0, new List<string>(), 0, 0);
            }

            // Calculate total duration and total number of exercises
            int totalDuration = 0;
            Dictionary<string, int> exerciseCount = new Dictionary<string, int>();
            int totalExercises = 0;

            foreach (var workout in workouts)
            {
                totalDuration += MinuteCalculator.CalculateMinutes(workout.Duration);

                totalExercises += workout.Exercises.Count;
                Console.WriteLine($"Added {workout.Exercises.Count} exercises, Total exercises: {totalExercises}");

                foreach (var exercise in workout.Exercises)
                {
                    if (exerciseCount.ContainsKey(exercise))
                    {
                        exerciseCount[exercise]++;
                    }
                    else
                    {
                        exerciseCount[exercise] = 1;
                    }
                    Console.WriteLine($"Exercise: {exercise}, Count: {exerciseCount[exercise]}");
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
            Console.WriteLine($"Top 5 exercises: {string.Join(", ", topExercises)}");

            // Calculate total number of workouts
            int totalWorkouts = workouts.Count;
            Console.WriteLine($"Total workouts: {totalWorkouts}");

            // Calculate average number of exercises per workout
            int averageExercisesPerWorkout = totalExercises / totalWorkouts;
            Console.WriteLine($"Average exercises per workout: {averageExercisesPerWorkout}");

            return new ExercisesWorkoutStatistics(averageDuration, topExercises, totalWorkouts, averageExercisesPerWorkout);
        }

        private bool TryParseCustomDuration(string input, out double result)
        {
            result = 0;
            var parts = input.Split(':');
            if (parts.Length == 2 && int.TryParse(parts[0].Trim(), out int hours) && int.TryParse(parts[1].Trim(), out int minutes))
            {
                result = hours + minutes / 100.0;
                return true;
            }
            return false;
        }
    }
}
