package vcmsa.ci.myapplication21

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Flashcard : AppCompatActivity() {

    private val questions = arrayOf(
        "Donald Trump was born June 14,1946,in New York",
        "The Great Fire of London happened in 02 September 1666",
        "Napoleon was born in Spain",
        "World War I ended in 1918",
        "The Roman Empire fell in 476 AD"
    )

    private val answers = arrayOf(true, true, false, true, true)
    private var score = 0
    private var currentIndex = 0
    private val userAnswers = mutableListOf<Boolean>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard)

        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)



        fun loadQuestion() {
            if (currentIndex < questions.size) {
                txtQuestion.text = questions[currentIndex]
            } else {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userAnswers", userAnswers.toBooleanArray())
                startActivity(intent)
                finish()

            }
        }

        btnTrue.setOnClickListener {
            checkAnswer(true)
            loadQuestion()
        }
        btnFalse.setOnClickListener {
            checkAnswer(false)
            loadQuestion()
        }
        loadQuestion()
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]
        userAnswers.add(userAnswer)

        if (userAnswer == correct) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            score++
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
        }
        currentIndex++
        if (currentIndex < questions.size) {
            val btnMove = findViewById<TextView>(R.id.txtQuestion)
            btnMove.setOnClickListener {
                btnMove.text = questions[currentIndex]

                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", 0)
                intent.getStringArrayExtra("questions") ?: arrayOf()
                startActivity(intent)
                finish()
            }
        }
    }
}





