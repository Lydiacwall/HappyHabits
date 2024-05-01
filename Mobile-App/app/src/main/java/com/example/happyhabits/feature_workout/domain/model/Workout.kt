package com.example.happyhabits.feature_workout.domain.model

open class Workout (
    var type: String,
    var time: String,
    var notes:String?,
    val unitMeasurement: String,
    var quantity: Float?
    ){
}

class FastActivity(
    type: String,
    time: String,
    notes: String?,
    quantity: Float?,
    var elevation: Float
) : Workout(
    type = type,
    time = time,
    notes = notes,
    unitMeasurement = "km",
    quantity = quantity
) {
}


class Weights(
    time: String,
    notes: String?,
    quantity: Float?,
    var muscleGroup: String?,
    exercises: List<Exercise> = emptyList()
) : Workout(
    type = "Weights",
    time = time,
    notes = notes,
    unitMeasurement = "kg",
    quantity = quantity
) {
    companion object {
        data class Exercise(
            val name: String,
            val reps: Int,
            val sets: Int
        )
    }

    var exercisesList: MutableList<Exercise> = exercises.toMutableList()


    fun addWeightExercise(exercise: Exercise) {
        exercisesList.add(exercise)
    }
    fun getExercisesByMuscleGroup(muscleGroup: String): List<String> {
        return when (muscleGroup) {
            "Chest Exercises" -> chestExercises
            "Shoulder Exercises" -> shoulderExercises
            "Bicep Exercises" -> bicepExercises
            "Triceps Exercises" -> tricepsExercises
            "Leg Exercises" -> legExercises
            "Back Exercises" -> backExercises
            "Glute Exercises" -> gluteExercises
            "Ab Exercises" -> abExercises
            "Calves Exercises" -> calvesExercises
            "Forearm Exercises" -> forearmExercises
            else -> emptyList()
        }
    }

    val muscleGroups = listOf(
        "Chest Exercises",
        "Shoulder Exercises",
        "Bicep Exercises",
        "Triceps Exercises",
        "Leg Exercises",
        "Back Exercises",
        "Glute Exercises",
        "Ab Exercises",
        "Calves Exercises",
        "Forearm Exercises"
    )

    val chestExercises = listOf(
        "Bar Dip",
        "Bench Press",
        "Cable Chest Press",
        "Close-Grip Bench Press",
        "Close-Grip Feet-Up Bench Press",
        "Decline Bench Press",
        "Dumbbell Chest Fly",
        "Dumbbell Chest Press",
        "Dumbbell Decline Chest Press",
        "Dumbbell Floor Press",
        "Dumbbell Pullover",
        "Feet-Up Bench Press",
        "Floor Press",
        "Incline Bench Press",
        "Incline Dumbbell Press",
        "Incline Push-Up",
        "Kettlebell Floor Press",
        "Kneeling Incline Push-Up",
        "Kneeling Push-Up",
        "Machine Chest Fly",
        "Machine Chest Press",
        "Pec Deck",
        "Pin Bench Press",
        "Push-Up",
        "Push-Up Against Wall",
        "Push-Ups With Feet in Rings",
        "Resistance Band Chest Fly",
        "Smith Machine Bench Press",
        "Smith Machine Incline Bench Press",
        "Standing Cable Chest Fly",
        "Standing Resistance Band Chest Fly"
    )

    val shoulderExercises = listOf(
        "Band External Shoulder Rotation",
        "Band Internal Shoulder Rotation",
        "Band Pull-Apart",
        "Barbell Front Raise",
        "Barbell Rear Delt Row",
        "Barbell Upright Row",
        "Behind the Neck Press",
        "Cable Lateral Raise",
        "Cable Rear Delt Row",
        "Dumbbell Front Raise",
        "Dumbbell Horizontal Internal Shoulder Rotation",
        "Dumbbell Horizontal External Shoulder Rotation",
        "Dumbbell Lateral Raise",
        "Dumbbell Rear Delt Row",
        "Dumbbell Shoulder Press",
        "Face Pull",
        "Front Hold",
        "Lying Dumbbell External Shoulder Rotation",
        "Lying Dumbbell Internal Shoulder Rotation",
        "Machine Lateral Raise",
        "Machine Shoulder Press",
        "Monkey Row",
        "Overhead Press",
        "Plate Front Raise",
        "Power Jerk",
        "Push Press",
        "Reverse Cable Flyes",
        "Reverse Dumbbell Flyes",
        "Reverse Machine Fly",
        "Seated Dumbbell Shoulder Press",
        "Seated Barbell Overhead Press",
        "Seated Smith Machine Shoulder Press",
        "Snatch Grip Behind the Neck Press",
        "Squat Jerk",
        "Split Jerk"
    )

    val bicepExercises = listOf(
        "Barbell Curl",
        "Barbell Preacher Curl",
        "Bodyweight Curl",
        "Cable Curl With Bar",
        "Cable Curl With Rope",
        "Concentration Curl",
        "Dumbbell Curl",
        "Dumbbell Preacher Curl",
        "Hammer Curl",
        "Incline Dumbbell Curl",
        "Machine Bicep Curl",
        "Spider Curl"
    )

    val tricepsExercises = listOf(
        "Barbell Standing Triceps Extension",
        "Barbell Lying Triceps Extension",
        "Bench Dip",
        "Close-Grip Push-Up",
        "Dumbbell Lying Triceps Extension",
        "Dumbbell Standing Triceps Extension",
        "Overhead Cable Triceps Extension",
        "Tricep Bodyweight Extension",
        "Tricep Pushdown With Bar",
        "Tricep Pushdown With Rope"
    )

    val forearmExercises = listOf(
        "Barbell Wrist Curl",
        "Barbell Wrist Curl Behind the Back",
        "Bar Hang",
        "Dumbbell Wrist Curl",
        "Farmers Walk",
        "Fat Bar Deadlift",
        "Gripper",
        "One-Handed Bar Hang",
        "Plate Pinch",
        "Plate Wrist Curl",
        "Towel Pull-Up",
        "Barbell Wrist Extension",
        "Dumbbell Wrist Extension"
    )

    val legExercises = listOf(
        "Air Squat",
        "Barbell Hack Squat",
        "Barbell Lunge",
        "Barbell Walking Lunge",
        "Belt Squat",
        "Body Weight Lunge",
        "Bodyweight Leg Curl",
        "Box Squat",
        "Bulgarian Split Squat",
        "Chair Squat",
        "Dumbbell Lunge",
        "Dumbbell Squat",
        "Front Squat",
        "Goblet Squat",
        "Hack Squat Machine",
        "Half Air Squat",
        "Hip Adduction Machine",
        "Jumping Lunge",
        "Landmine Hack Squat",
        "Landmine Squat",
        "Leg Curl On Ball",
        "Leg Extension",
        "Leg Press",
        "Lying Leg Curl",
        "Pause Squat",
        "Romanian Deadlift",
        "Safety Bar Squat",
        "Seated Leg Curl",
        "Shallow Body Weight Lunge",
        "Side Lunges (Bodyweight)",
        "Smith Machine Squat",
        "Squat",
        "Step Up",
        "Zercher Squat"
    )

    val backExercises = listOf(
        "Assisted Chin-Up",
        "Assisted Pull-Up",
        "Back Extension",
        "Banded Muscle-Up",
        "Barbell Row",
        "Barbell Shrug",
        "Block Clean",
        "Block Snatch",
        "Cable Close Grip Seated Row",
        "Cable Wide Grip Seated Row",
        "Chin-Up",
        "Clean",
        "Clean and Jerk",
        "Deadlift",
        "Deficit Deadlift",
        "Dumbbell Deadlift",
        "Dumbbell Row",
        "Dumbbell Shrug",
        "Floor Back Extension",
        "Good Morning",
        "Hang Clean",
        "Hang Power Clean",
        "Hang Power Snatch",
        "Hang Snatch",
        "Inverted Row",
        "Inverted Row with Underhand Grip",
        "Jefferson Curl",
        "Jumping Muscle-Up",
        "Kettlebell Swing",
        "Lat Pulldown With Pronated Grip",
        "Lat Pulldown With Supinated Grip",
        "Muscle-Up (Bar)",
        "Muscle-Up (Rings)",
        "One-Handed Cable Row",
        "One-Handed Lat Pulldown",
        "Pause Deadlift",
        "Pendlay Row",
        "Power Clean",
        "Power Snatch",
        "Pull-Up",
        "Pull-Up With a Neutral Grip",
        "Rack Pull",
        "Seal Row",
        "Seated Machine Row",
        "Snatch",
        "Snatch Grip Deadlift",
        "Stiff-Legged Deadlift",
        "Straight Arm Lat Pulldown",
        "Sumo Deadlift",
        "T-Bar Row",
        "Trap Bar Deadlift With High Handles",
        "Trap Bar Deadlift With Low Handles"
    )

    val gluteExercises = listOf(
        "Banded Side Kicks",
        "Cable Pull Through",
        "Clamshells",
        "Dumbbell Romanian Deadlift",
        "Dumbbell Frog Pumps",
        "Fire Hydrants",
        "Frog Pumps",
        "Glute Bridge",
        "Hip Abduction Against Band",
        "Hip Abduction Machine",
        "Hip Thrust",
        "Hip Thrust Machine",
        "Hip Thrust With Band Around Knees",
        "Lateral Walk With Band",
        "Machine Glute Kickbacks",
        "One-Legged Glute Bridge",
        "One-Legged Hip Thrust",
        "Romanian Deadlift",
        "Single Leg Romanian Deadlift",
        "Standing Glute Kickback in Machine",
        "Step Up"
    )

    val abExercises = listOf(
        "Ball Slams",
        "Cable Crunch",
        "Crunch",
        "Dead Bug",
        "Hanging Knee Raise",
        "Hanging Leg Raise",
        "Hanging Sit-Up",
        "High to Low Wood Chop with Band",
        "Horizontal Wood Chop with Band",
        "Kneeling Ab Wheel Roll-Out",
        "Kneeling Plank",
        "Kneeling Side Plank",
        "Lying Leg Raise",
        "Lying Windshield Wiper",
        "Lying Windshield Wiper with Bent Knees",
        "Machine Crunch",
        "Mountain Climbers",
        "Oblique Crunch",
        "Oblique Sit-Up",
        "Plank",
        "Plank with Leg Lifts",
        "Side Plank",
        "Sit-Up"
    )

    val calvesExercises = listOf(
        "Eccentric Heel Drop",
        "Heel Raise",
        "Seated Calf Raise",
        "Standing Calf Raise"
    )
}

class YogaOrSwimming(
    type: String,
    time: String,
    notes: String?,
    exercises: List<String> = emptyList()
) : Workout(
    type = type,
    time = time,
    notes = notes,
    unitMeasurement = "",
    quantity = null
) {
    var exercisesList: MutableList<String> = exercises.toMutableList()


    fun addYogaOrSwimmingExercise(exercise: String) {
        exercisesList.add(exercise)
    }
}

