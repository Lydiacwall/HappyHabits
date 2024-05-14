using Happy_Habits_App.Forms;

namespace Happy_Habits_App.Services
{
    public interface IExercisesWorkoutActivitiesService
    {
        public Task AddExerciseWorkoutActivity(ExercisesWorkoutForm form);
    }
}
