using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

namespace Happy_Habits_App.Services
{
    public class SleepActivitiesService : ISleepActivitiesService
    {
        private readonly ISleepActivitiesRepository _sleepActivitiesRepository;
        private readonly IUserRepository _userRepository;
        public SleepActivitiesService(ISleepActivitiesRepository sleepActivitiesRepository, IUserRepository userRepository)
        {
            _sleepActivitiesRepository = sleepActivitiesRepository;
            _userRepository = userRepository;
        }

        public async Task AddSleepActivity(SleepForm form)
        {
            await _sleepActivitiesRepository.DeleteSleepActivityAsync(form.UserId, DateOnly.Parse(form.Date));
            await _sleepActivitiesRepository.CreateSleepActivityAsybc(
                new Sleep(DateOnly.Parse(form.Date), form.UserId, form.Time, form.Quality));
        }

        public Task<List<Sleep>> GetAllSleepActivities()
        {
            throw new NotImplementedException();
        }

        public async Task<SleepStatistics> GetStatistics(string userId, string monday, string sunday)
        {
            List<Sleep> sleepHabits = await _sleepActivitiesRepository.GetSleepActivitiesByMonthAndUserAsync(monday, sunday, userId);

            int sleepgoal = await _userRepository.GetSleepGoalById(userId);

            int totalMinutes = 0;

            foreach (var sleep in sleepHabits)
            {
                float time = float.Parse(sleep.Time);
                int hours = (int)(time / 60);
                int minutes = (int)(time % 60);
                totalMinutes += hours * 60 + minutes;
            }

            double averageMinutes = (int)(totalMinutes / sleepHabits.Count);

            // Convert the average sleep time back to hours and minutes
            int averageHours = (int)averageMinutes / 60;
            int averageRemainingMinutes = (int)averageMinutes % 60;

            // Convert sleep goal to minutes
            int sleepGoalMinutes = sleepgoal * 60;

            // Convert actual sleep time to minutes
            int actualSleepTotalMinutes = averageHours * 60 + averageRemainingMinutes;

            // Calculate the difference in minutes
            int differenceMinutes = sleepGoalMinutes - actualSleepTotalMinutes;

            // Convert the difference back to hours and minutes
            int differenceHours = Math.Abs(differenceMinutes) / 60;
            int remainingMinutes = Math.Abs(differenceMinutes) % 60;

            Dictionary<string, string> timeSlept = new Dictionary<string, string>();
            foreach (var sleep in sleepHabits)
            {
                timeSlept[sleep.Date.ToString()] = sleep.Time;
            }

            DateTime startDate = DateTime.Parse(monday);
            DateTime endDate = DateTime.Parse(sunday);
            List<float> sleepDurations = new List<float>();

            for (DateTime date = startDate; date <= endDate; date = date.AddDays(1))
            {
                string dateString = date.ToString("yyyy-MM-dd");
                if (timeSlept.TryGetValue(dateString, out string sleepTime))
                {
                    if (float.TryParse(sleepTime, out float sleepDuration))
                    {
                        sleepDurations.Add(sleepDuration);
                    }
                    else
                    {
                        sleepDurations.Add(0.0f);
                    }
                }
                else
                {
                    sleepDurations.Add(0.0f);
                }
            }
                SleepStatistics statistics = new SleepStatistics(
                sleepDurations, averageHours, averageRemainingMinutes, differenceHours, remainingMinutes);
            return statistics;
        }
    }
}