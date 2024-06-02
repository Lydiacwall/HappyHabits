using DinkToPdf;
using DinkToPdf.Contracts;
using SharpCompress.Common;
using System.Text.Json;

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

            var sleepDurations = formData["sleepDurations"] as JsonElement?;

            var sleepDurationList = sleepDurations.Value.EnumerateArray().Select(x => x.GetInt32()).ToList();
            htmlContent += "<h2>Sleep Time in the week</h2>";
            htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
            htmlContent += "<tr><th>Day</th><th>Sleep Time</th></tr>";
            for (int i = 0; i < sleepDurationList.Count; i++)
            {
                int hours = sleepDurationList[i] / 60;
                int minutes = sleepDurationList[i] % 60;
                htmlContent += $"<tr><td>{days[i]}</td><td>{hours} hours {minutes} minutes</td></tr>";
            }
            htmlContent += "</table><br/>";

            var statistics = (JsonElement)formData["statistics"];

            int dailyAverageHours = (int)statistics.GetProperty("dailyAverageHours").GetDouble();
            int dailyAverageMinutes = (int)statistics.GetProperty("dailyAverageMinutes").GetDouble();
            int differenceInHours = (int)statistics.GetProperty("differenceInHours").GetDouble();
            int differenceInMinutes = (int)statistics.GetProperty("differenceInMinutes").GetDouble();
            string mostFrequentQuality = statistics.GetProperty("mostFrequentQuality").GetString();

            htmlContent += $"<p>The client spent <strong>{dailyAverageHours}</strong> hours and <strong>{dailyAverageMinutes}</strong> minutes on average sleeping the week</p>";
            htmlContent += $"<p>Also the client slept on average <strong>{differenceInHours}</strong> hours and <strong>{differenceInMinutes}</strong> minutes</p>";
            htmlContent += $"<p>Last but not least the client slept mostly <strong>{mostFrequentQuality}</strong> the week</p>";

            htmlContent += "</body></html>";

            var pdfDoc = new HtmlToPdfDocument
            {
                GlobalSettings = { PaperSize = PaperKind.A4 },
                Objects = { new ObjectSettings { HtmlContent = htmlContent } }
            };

            return _converter.Convert(pdfDoc);
        }

        private string FormatKey(string key)
        {
            // Replace camelCase or PascalCase with space-separated words and capitalize each word
            return System.Text.RegularExpressions.Regex.Replace(key, "(\\B[A-Z])", " $1");
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