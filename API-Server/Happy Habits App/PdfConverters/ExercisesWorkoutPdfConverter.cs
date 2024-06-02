
using DinkToPdf;
using DinkToPdf.Contracts;
using SharpCompress.Common;
using System.Text.Json;

namespace Happy_Habits_App.PdfConverters
{
    public class ExercisesWorkoutPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public ExercisesWorkoutPdfConverter(IConverter converter)
        {
            _converter = converter;
        }
        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body><h1>Form Data</h1>";
            htmlContent += $"<h1>{clientUsername}'s ExerciseWorkout Statistics</h1>";

            JsonElement element = (JsonElement)formData["averageDuration"];
            var duration = element.GetDouble();
            int hours = (int)duration / 60;
            int minutes = (int)duration % 60;
            htmlContent += $"<p>The client in the month had average duration of <strong> {hours} </strong> hours and <strong> {minutes} </strong> minutes </p>";

            htmlContent += $"<p>The client had an average of <strong>{formData["averageExercisePerWorkout"]}</strong> exercises per workout</p>";
            htmlContent += $"<p>The client had a total of <strong>{formData["totalWorkouts"]}</strong> workouts</p>";

            if (formData["topExercises"] is JsonElement jsonElementExercises && jsonElementExercises.ValueKind == JsonValueKind.Array)
            {
                var exercises = new List<string>();
                foreach (JsonElement elementData in jsonElementExercises.EnumerateArray())
                {
                    exercises.Add(elementData.GetString());
                }

                htmlContent += "<h2>Top 5 Exercises Workouts</h2>";
                htmlContent += "<table border='1' style='width:100%; border-collapse: collapse;'>";
                htmlContent += "<tr><th>Exercise</th></tr>";
                foreach (var exercise in exercises)
                {
                    htmlContent += $"<tr><td>{exercise}</td></tr>";
                }
                htmlContent += "</table><br/>";
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

/* {
  "senderId": "6633665f563dbd9d22f06d9d",
  "groupId": "string",
  "type": "ExercisesWorkout",
    "statistics": {
        "averageDuration": 75.5,
  "topExercises": [
    "Push-ups",
    "Pull-ups",
    "Squats",
    "Lunges",
    "Plank"
  ],
  "totalWorkouts": 20,
  "averageExercisePerWorkout": 5    }
}*/