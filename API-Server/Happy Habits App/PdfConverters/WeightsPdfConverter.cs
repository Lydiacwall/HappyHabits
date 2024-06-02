
using DinkToPdf;
using DinkToPdf.Contracts;
using System.Text.Json;

namespace Happy_Habits_App.PdfConverters
{
    public class WeightsPdfConverter : IPdfConverter
    {
        private readonly IConverter _converter;

        public WeightsPdfConverter(IConverter converter)
        {
            _converter = converter;
        }
        public byte[] GeneratePdfFromFormData(Dictionary<string, object> formData, string clientUsername)
        {
            string htmlContent = "<html><body><h1>Form Data</h1>";
            htmlContent += $"<h1>{clientUsername}'s Weights Statistics</h1>";

            foreach (var entry in formData)
            {
                switch (entry.Key)
                {
                    case "averageDuration":
                        if (entry.Value is JsonElement jsonElementDuration && jsonElementDuration.ValueKind == JsonValueKind.Number)
                        {
                            double duration = jsonElementDuration.GetDouble();
                            int hours = (int)duration / 60;
                            int minutes = (int)duration % 60;
                            htmlContent += $"<p>The client in the week had average duration: <strong>{hours} hours</strong> and <strong>{minutes} minutes</strong></p>";
                        }
                        break;


                    case "totalWorkouts":
                        if (entry.Value is JsonElement jsonElementTotalWorkouts && jsonElementTotalWorkouts.ValueKind == JsonValueKind.Number)
                        {
                            int totalWorkouts = jsonElementTotalWorkouts.GetInt32();
                            htmlContent += $"<p>The client had a total of <strong>{totalWorkouts}</strong> workouts</p>";
                        }
                        break;

                    case "averageExercisePerWorkout":
                        if (entry.Value is JsonElement jsonElementAvgExercisePerWorkout && jsonElementAvgExercisePerWorkout.ValueKind == JsonValueKind.Number)
                        {
                            int avgExercisesPerWorkout = jsonElementAvgExercisePerWorkout.GetInt32();
                            htmlContent += $"<p>The client had an average of <strong>{avgExercisesPerWorkout}</strong> exercises per workout</p>";
                        }
                        break;

                    case "averageKgsPerWorkout":
                        if (entry.Value is JsonElement jsonElementAvgKgsPerWorkout && jsonElementAvgKgsPerWorkout.ValueKind == JsonValueKind.Number)
                        {
                            float avgKgsPerWorkout = jsonElementAvgKgsPerWorkout.GetSingle();
                            htmlContent += $"<p>The client lifted an average of <strong>{avgKgsPerWorkout} kgs</strong> per workout</p>";
                        }
                        break;

                    case "topExercises":
                        if (entry.Value is JsonElement jsonElementExercises && jsonElementExercises.ValueKind == JsonValueKind.Array)
                        {
                            var exercises = new List<string>();
                            foreach (JsonElement element in jsonElementExercises.EnumerateArray())
                            {
                                exercises.Add(element.GetString());
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
  "type": "ExercisesWorkout",
    "statistics": {
        "averageDuration": 60.2,
  "topExercises": [
    "Bench Press",
    "Deadlift",
    "Bicep Curls",
    "Tricep Dips",
    "Leg Press"
  ],
  "totalWorkouts": 15,
  "averageExercisePerWorkout": 4,
  "averageKgsPerWorkout": 35.5   }
}
 */