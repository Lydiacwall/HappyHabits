using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using DinkToPdf;
using DinkToPdf.Contracts;

namespace Happy_Habits_App.PdfConverters
{
    public class SleepPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public SleepPdfConverter(IConverter converter)
        {
            _converter = converter;
        }

        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body>";
            htmlContent += $"<h1>{clientUsername}'s Sleep Statistics</h1>";

            string[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

            if (formData.TryGetValue("sleepDurations", out var sleepDurationsObj) &&
                sleepDurationsObj is JsonElement sleepDurations &&
                sleepDurations.ValueKind == JsonValueKind.Array)
            {
                var sleepDurationList = sleepDurations.EnumerateArray()
                                                      .Select(x => x.TryGetDouble(out double value) ? value : (double?)null)
                                                      .Where(x => x.HasValue)
                                                      .Select(x => x.Value)
                                                      .ToList();
                htmlContent += "<h2>Sleep Time in the Week</h2>";
                htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
                htmlContent += "<tr><th>Day</th><th>Sleep Time</th></tr>";
                for (int i = 0; i < sleepDurationList.Count && i < days.Length; i++)
                {
                    int hours = (int)sleepDurationList[i] / 60;
                    int minutes = (int)sleepDurationList[i] % 60;
                    htmlContent += $"<tr><td>{days[i]}</td><td>{hours} hours {minutes} minutes</td></tr>";
                }
                htmlContent += "</table><br/>";
            }
            else
            {
                htmlContent += "<p>Sleep durations data is not available.</p>";
            }

            if (formData.TryGetValue("dailyAverageHours", out var avgHoursObj) &&
                avgHoursObj is JsonElement jsonElementAvgHours &&
                jsonElementAvgHours.TryGetDouble(out double dailyAverageHours))
            {
                htmlContent += $"<p>Daily Average Hours: <strong>{dailyAverageHours}</strong></p>";
            }

            if (formData.TryGetValue("dailyAverageMinutes", out var avgMinutesObj) &&
                avgMinutesObj is JsonElement jsonElementAvgMinutes &&
                jsonElementAvgMinutes.TryGetDouble(out double dailyAverageMinutes))
            {
                htmlContent += $"<p>Daily Average Minutes: <strong>{dailyAverageMinutes}</strong></p>";
            }

            if (formData.TryGetValue("differenceInHours", out var diffHoursObj) &&
                diffHoursObj is JsonElement jsonElementDifferenceInHours &&
                jsonElementDifferenceInHours.TryGetDouble(out double differenceInHours))
            {
                htmlContent += $"<p>Difference in Hours: <strong>{differenceInHours}</strong></p>";
            }

            if (formData.TryGetValue("differenceInMinutes", out var diffMinutesObj) &&
                diffMinutesObj is JsonElement jsonElementDifferenceInMinutes &&
                jsonElementDifferenceInMinutes.TryGetDouble(out double differenceInMinutes))
            {
                htmlContent += $"<p>Difference in Minutes: <strong>{differenceInMinutes}</strong></p>";
            }

            htmlContent += "</body></html>";

            var pdfDoc = new HtmlToPdfDocument
            {
                GlobalSettings = { PaperSize = PaperKind.A4 },
                Objects = { new ObjectSettings { HtmlContent = htmlContent } }
            };

            return _converter.Convert(pdfDoc);
        }
    }
}

/*
{
  "senderId": "6633665f563dbd9d22f06d9d",
  "groupId": "string",
  "type": "Sleep",
  "statistics": {
      "sleepDurations": [420, 480, 450, 470, 460, 490, 500],
      "dailyAverageHours": 7,
      "dailyAverageMinutes": 30,
      "differenceInHours": 0,
      "differenceInMinutes": 0,
      "mostFrequentQuality": "Good"
  }
}
*/