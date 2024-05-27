using Happy_Habits_App.Forms;
using Happy_Habits_App.Repositories;
using Happy_Habits_App.Model;

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

            if (workouts == null || workouts.Count == 0)
            {
                return new ExercisesWorkoutStatistics(0, new List<string>(), 0, 0);
            }

            // Calculate total duration and total number of exercises
            double totalDuration = 0;
            Dictionary<string, int> exerciseCount = new Dictionary<string, int>();
            int totalExercises = 0;

            foreach (var workout in workouts)
            {
                if (double.TryParse(workout.Duration, out double duration))
                {
                    totalDuration += duration;
                }

                totalExercises += workout.Exercises.Count;

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
                }
            }

            // Calculate average duration
            double averageDuration = totalDuration / workouts.Count;

            // Identify top 5 exercises
            var topExercises = exerciseCount.OrderByDescending(ec => ec.Value)
                                             .Take(5)
                                             .Select(ec => ec.Key)
                                             .ToList();

            // Calculate total number of workouts
            int totalWorkouts = workouts.Count;

            // Calculate average number of exercises per workout
            double averageExercisesPerWorkout = (double)totalExercises / totalWorkouts;

            return new ExercisesWorkoutStatistics(averageDuration, topExercises, totalWorkouts, averageExercisesPerWorkout);
        }
    }
}
