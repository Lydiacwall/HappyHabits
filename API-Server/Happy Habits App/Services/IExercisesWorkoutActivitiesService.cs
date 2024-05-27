using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IExercisesWorkoutActivitiesService
    {
        public Task AddExerciseWorkoutActivity(ExercisesWorkoutForm form);
        Task<ExercisesWorkoutStatistics> GetWorkoutStatistics(string userId, int month, int year, string type);
    }
}
