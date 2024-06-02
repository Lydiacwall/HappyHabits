using DinkToPdf;
using DinkToPdf.Contracts;
using System.Text.Json;

namespace Happy_Habits_App.PdfConverters
{
    public class MoodPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public MoodPdfConverter(IConverter converter)
        {
            _converter = converter;
        }

        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body>";
            htmlContent += $"<h1>{clientUsername}'s Mood Records</h1>";

            if (formData.ContainsKey("moodMap"))
            {
                var moodMap = (JsonElement)formData["moodMap"];
                if (moodMap.ValueKind == JsonValueKind.Object)
                {
                    htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
                    htmlContent += "<tr><th>Date</th><th>Mood</th></tr>";

                    foreach (JsonProperty moodEntry in moodMap.EnumerateObject())
                    {
                        string date = moodEntry.Name;
                        string mood = moodEntry.Value.GetString();
                        htmlContent += $"<tr><td>{date}</td><td>{mood}</td></tr>";
                    }

                    htmlContent += "</table>";
                }
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
 *  {
  "senderId": "6633665f563dbd9d22f06d9d",
  "groupId": "string",
  "type": "Mood",
    "statistics": {
        
  "moodMap": {
    "06/01/2024": "Great",
    "06/02/2024": "Meh",
    "06/03/2024": "Fine",
    "06/04/2024": "Terrible",
    "06/05/2024": "Great",
    "06/06/2024": "Fine",
    "06/07/2024": "Meh"
  }

 }
}*/