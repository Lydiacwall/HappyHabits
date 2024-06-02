
using DinkToPdf;
using DinkToPdf.Contracts;
using System.Text.Json;

namespace Happy_Habits_App.PdfConverters
{
    public class SymptomPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;
        public SymptomPdfConverter(IConverter converter) 
        {
            _converter = converter;
        }
        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body><h1>Form Data</h1>";
            htmlContent += $"<h1>{clientUsername}'s Symptom Statistics</h1>";
            foreach (var entry in formData)
            {
                switch (entry.Key)
                {
                    case "symptomList":
                        if (entry.Value is JsonElement jsonElementExercises && jsonElementExercises.ValueKind == JsonValueKind.Array)
                        {
                            var exercises = new List<string>();
                            foreach (JsonElement element in jsonElementExercises.EnumerateArray())
                            {
                                exercises.Add(element.GetString());
                            }

                            htmlContent += "<h2>Top 5 Symptoms</h2>";
                            htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
                            htmlContent += "<tr><th>Exercise</th></tr>";
                            foreach (var exercise in exercises)
                            {
                                htmlContent += $"<tr><td>{exercise}</td></tr>";
                            }
                            htmlContent += "</table><br/>";
                        }
                        break;

                    default:
                        htmlContent += $"<p>{entry.Key}: {entry.Value}</p>";
                        break;
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
  {
  "senderId": "6633665f563dbd9d22f06d9d",
  "groupId": "string",
  "type": "Symptom",
    "statistics": {
        "symptomList": [
    "Headache",
    "Fever",
    "Fatigue",
    "Nausea",
    "Cough"
  ] }
}
 */