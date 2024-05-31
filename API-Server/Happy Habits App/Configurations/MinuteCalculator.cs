namespace Happy_Habits_App.Configurations
{
    public class MinuteCalculator
    {
        public static int CalculateMinutes(string time)
        {
            // Split the string by the colon
            string[] timeParts = time.Split(':');

            // Parse the hours and minutes
            int hours = int.Parse(timeParts[0].Trim());
            int minutes = int.Parse(timeParts[1].Trim());

            // Calculate the total number of minutes
            int totalMinutes = (hours * 60) + minutes;

            return totalMinutes;
        }
    }
}
