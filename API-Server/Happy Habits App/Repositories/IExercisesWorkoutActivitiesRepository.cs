using Happy_Habits_App.Model;

namespace Happy_Habits_App.Repositories
{
    public interface IExercisesWorkoutActivitiesRepository
    {
        Task CreateExerciseWorkoutActivity(ExercisesWorkout exercisesWorkout);
        Task<List<ExercisesWorkout>> GetExercisesWorkoutActivitiesByUserAndDate(string userId, int month, int year, string type);
    }
}
