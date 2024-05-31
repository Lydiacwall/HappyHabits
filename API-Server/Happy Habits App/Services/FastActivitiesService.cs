using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;
using System.Globalization;

namespace Happy_Habits_App.Services
{
    public class FastActivitiesService : IFastActivitiesService
    {
        private readonly IFastActivitiesRepository _fastActivitiesRepository;
        public FastActivitiesService(IFastActivitiesRepository fastActivitiesRepository)
        {
            _fastActivitiesRepository = fastActivitiesRepository;
        }

        public async Task AddFastActivity(FastActivityForm form)
        {
            await _fastActivitiesRepository.CreateFastActivityAsync(
                new FastActivity(
                    DateOnly.Parse(form.Date),
                    form.UserId,
                    form.Type,
                    form.Time,
                    form.Duration,
                    form.Notes,
                    "km",
                    form.Quantity,
                    form.Elevation
                    ));
        }
        public async Task<FastActivitiesStatistics> GetFastActivitiesStatisticsAsync(string userId, int month, int year, string type)
        {
            var fastActivities = await _fastActivitiesRepository.GetFastActivitiesByCriteriaAsync(userId, month, year, type);

            var totalWorkouts = fastActivities.Count;
            var totalQuantity = fastActivities.Sum(fa => fa.Quantity);
            var totalElevation = fastActivities.Sum(fa => fa.Elevation);
            var totalDuration = fastActivities.Sum(fa => TimeSpan.Parse(fa.Duration, CultureInfo.InvariantCulture).TotalMinutes);


            var averageQuantity = totalWorkouts > 0 ? totalQuantity / totalWorkouts : 0;
            var averageElevation = totalWorkouts > 0 ? totalElevation / totalWorkouts : 0;
            var averageDuration = totalWorkouts > 0 ? totalDuration / totalWorkouts : 0;

            return new FastActivitiesStatistics
            {
                AverageQuantity = averageQuantity,
                AverageElevation = averageElevation,
                AverageDuration = averageDuration,
                TotalQuantity = totalQuantity,
                TotalWorkouts = totalWorkouts
            };
        }
    }
}
