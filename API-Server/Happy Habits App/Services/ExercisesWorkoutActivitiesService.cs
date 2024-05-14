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
    }
}
