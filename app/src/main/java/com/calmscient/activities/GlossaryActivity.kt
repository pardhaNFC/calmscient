/*
 *
 *      Copyright (c) 2023- NFC Solutions, - All Rights Reserved
 *      All source code contained herein remains the property of NFC Solutions Incorporated
 *      and protected by trade secret or copyright law of USA.
 *      Dissemination, De-compilation, Modification and Distribution are strictly prohibited unless
 *      there is a prior written permission or license agreement from NFC Solutions.
 *
 *      Author : @Pardha Saradhi
 */

package com.calmscient.activities

import com.calmscient.R
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmscient.adapters.GlossaryAdapter
import com.calmscient.di.remote.Task
import com.calmscient.databinding.LayoutGlossaryBinding


class GlossaryActivity : AppCompat() {
    lateinit var binding: LayoutGlossaryBinding
    private val glossaryAdapter = GlossaryAdapter(mutableListOf())
    private val taskWorkData = mutableListOf<Task>()
    /*private val tasks = listOf(
        Task(
            "A",
            getString(com.calmscient.R.string.adrenaline),
            "Adrenaline, also known as epinephrine, is a hormone and neurotransmitter produced by the adrenal glands, which are located on top of the kidneys. It plays a crucial role in the body's response to stress and triggering the \"Fight or Flight\" response.\n" +
                    "When a person perceives a threat or danger, whether real or perceived, the brain signals the adrenal glands to release adrenaline into the bloodstream. Adrenaline acts as a chemical messenger, rapidly preparing the body for action to deal with the perceived threat.\n"
        ),
        Task(
            "A",
            getString(com.calmscient.R.string.anxiety_attack),
            "The term \"anxiety attack\" is not officially recognized in the diagnostic manuals; DSM-5 (Diagnostic and Statistical Manual of Mental Disorders). It is often used to describe a period of heightened anxiety or a more prolonged state of anxiousness. Anxiety attacks may involve similar symptoms to panic attacks, such as excessive worry, restlessness, irritability, muscle tension, difficulty concentrating, and sleep disturbances."
        ),
        Task(
            "A",
            "Automatic thinking",
            "Automatic thinking refers to the rapid, involuntary, and often unconscious thoughts that arise in response to situations or triggers. These thoughts are automatic in the sense that they occur automatically and effortlessly, without deliberate intention or conscious awareness.\n" +
                    "Automatic thinking patterns can be influenced by past experiences, beliefs, and cognitive biases. They can be helpful or unhelpful, depending on their content and impact on emotions and behaviors. In the context of anxiety, automatic thoughts often lean towards negative and worrisome interpretations of situations, leading to increased anxiety and distress.\n"
        ),

        Task(
            "B",
            "Biased thinking",
            "Biased thinking refers to cognitive distortions or errors in thinking that influence how we perceive and interpret information. It involves a tendency to view situations, events, or ourselves in a distorted or exaggerated manner, often leading to negative or irrational thoughts. Biased thinking can contribute to the development and maintenance of anxiety and other mental health issues."
        ),
        Task(
            "C",
            "Cortisol",
            "Cortisol is a hormone produced by the adrenal glands in response to stress. It is often referred to as the primary stress hormone. When the brain detects a threat or perceives stress, it signals the release of cortisol into the bloodstream. While cortisol is important for short-term stress adaptation, chronic or prolonged elevation of cortisol levels due to chronic stress can cause a loss of neurons in the prefrontal cortex and hippocampus. It also decreases serotonin, the “happy hormone”, which makes a person feel angry, depressed, and feel physical pain more quickly and easily."
        ),
        Task(
            "C",
            "Cognitive Distortion",
            "Cognitive distortions, also known as biased thinking or irrational thoughts, refer to exaggerated or irrational patterns of thinking that can contribute to emotional distress and maintain anxiety or other mental health issues.\n" +
                    "Cognitive distortions are common and can affect the way individuals interpret and perceive events, situations, and themselves. They often involve biases or errors in reasoning that lead to distorted or negative thinking patterns.\n"
        ),
        Task(
            "C",
            "Competency",
            "Competency refers to the ability to perform or execute a task or set of skills effectively. It is often associated with knowledge, expertise, and proficiency in a particular area. Competency is measured by evaluating our aptitude, qualifications, or capabilities in a specific situation."
        ),
        Task(
            "C",
            "Compulsive behaviour",
            "Compulsive behavior refers to repetitive and often ritualistic actions or thoughts that individuals feel driven to perform in response to anxiety or distressing thoughts. These behaviors are typically aimed at reducing anxiety, preventing perceived harm, or maintaining a sense of control. However, they are often excessive, time-consuming, and interfere with daily functioning."
        ),
        Task(
            "D",
            "Dependency",
            "Dependency refers to reliance on substances such as alcohol, drugs, or tobacco as a means of coping with anxiety or seeking quick relief or escape from anxiety or help coping with overwhelming emotions. However, substance dependency can be problematic and can lead to long-term physical, psychological, and social consequences."
        ),
        Task(
            "G",
            "GABA",
            "GABA stands for gamma-aminobutyric acid, which is a neurotransmitter in the brain. It is known as the brain's natural anti-anxiety drug because it has inhibitory effects on the central nervous system, helping to reduce neuronal excitability and promote relaxation. GABA acts as a calming agent, counteracting the effects of excitatory neurotransmitters in the brain.\n" +
                    "By increasing GABA levels through exercise, individuals may experience a reduction in anxiety symptoms, improved mood, and an overall sense of well-being. This is one of the ways in which exercise can have a positive impact on mental health and help alleviate anxiety.\n"
        ),
        Task(
            "H",
            "Hyperventilation",
            "Hyperventilation refers to a rapid or excessive increase in breathing rate and volume. It involves taking in more oxygen and exhaling more carbon dioxide than the body needs. Hyperventilation can be a physiological response to anxiety or stress and is often associated with panic attacks."
        ),
        Task(
            "M",
            "Mindful breathing",
            "Mindful breathing is a technique that involves intentionally bringing awareness to the breath and focusing on the present moment. It is a fundamental practice in mindfulness and can help promote relaxation, reduce stress, and cultivate a sense of calm."
        ),
        Task(
            "O",
            "Obsession",
            "Obsession refers to intrusive, persistent, and unwanted thoughts, images, or urges that repeatedly and uncontrollably enter a person's mind. Individuals with obsessions often recognize that their thoughts are irrational or excessive, but they find it challenging to dismiss or ignore them. As a result, they may engage in various repetitive behaviors or mental rituals, known as compulsions, to alleviate the anxiety caused by the obsessions temporarily."
        ),
        Task(
            "P",
            "Panic attack",
            "A panic attack is a sudden and intense episode of fear or discomfort that typically peaks within minutes. It is a manifestation of anxiety and can occur without any apparent trigger or in response to a specific situation or object. Panic attacks can be accompanied by various physical and psychological symptoms, such as a rapid heart rate, chest pain, shortness of breath, dizziness, trembling, sweating, a sense of impending doom or loss of control, and feelings of unreality or detachment."
        ),
        Task(
            "P",
            "Progressive muscle relaxation",
            "Progressive muscle relaxation (PMR) is a relaxation technique that involves systematically tensing and relaxing different muscle groups in the body. It aims to reduce muscle tension, release physical stress, and promote overall relaxation."
        ),
        Task(
            "R",
            "Resilience",
            "Resilience refers to the capacity to bounce back, adapt, and recover in the face of adversity, challenges, or stressful situations. It is the ability to withstand, cope with, and effectively navigate through difficulties, setbacks, or hardships. Resilience involves psychological, emotional, and sometimes physical strength to maintain a positive mindset and being able to function despite adversity.\n" +
                    "Resilience is not about avoiding or eliminating challenges, but rather about how we respond and recover from them. It involves skills such as emotional regulation, problem-solving, flexibility, optimism, and social support. Resilience helps us persevere, learn from setbacks, and grow stronger as a result.\n"
        ),
        Task(
            "R",
            "Rigid thinking",
            "Rigid thinking refers to inflexible and narrow patterns of thinking that limit flexibility, adaptability, and open-mindedness. People with rigid thinking tend to adhere strictly to their beliefs, rules, and expectations without considering alternative perspectives or possibilities. They may have a black-and-white view of the world, categorizing things as either all good or all bad, with little room for shades of gray or ambiguity.\n" +
                    "When faced with challenges or uncertainties, individuals with rigid thinking may become overwhelmed or stuck, as they struggle to adapt and find alternative solutions. Rigid thinking can also lead to self-criticism, perfectionism, and a constant need for control, which can perpetuate anxiety and stress.\n"
        ),
        Task(
            "R",
            "Rumination",
            "The tendency to continuously and excessively think about negative events, situations, or problems. It involves repetitively focusing on one's distressing thoughts, often with a lack of resolution or progress toward a solution.\n" +
                    "Rumination is a common cognitive process associated with anxiety and other mental health conditions. When someone ruminates, they may replay past events, worry about future outcomes, or excessively analyze their thoughts and feelings. This prolonged dwelling on negative thoughts can contribute to heightened anxiety and a sense of feeling stuck or trapped in one's worries\n"
        ),
        Task(
            "S",
            "Self-compassion",
            "Self-compassion refers to the practice of treating oneself with kindness, understanding, and non-judgment during times of difficulty, failure, or suffering. It involves extending the same warmth, care, and support to oneself that one would offer to a close friend or loved one who is struggling. "
        ),
        Task(
            "S",
            "Serotonin",
            "Serotonin is a neurotransmitter, a chemical messenger in the brain and nervous system that plays a crucial role in regulating various physiological and psychological processes. It is often referred to as the \"feel-good\" neurotransmitter because of its association with mood regulation and feelings of well-being."
        ),
        Task(
            "W",
            "Window of tolerance",
            "Window of tolerance refers to a concept in psychology that describes an optimal range of arousal or activation that allows an individual to effectively cope with and respond to stressors and emotions. The window of tolerance suggests that individuals have a range in which they can handle and adapt to stressors without becoming overwhelmed or shutting down. When a person is within their window of tolerance, they are able to think clearly, make rational decisions, and engage in effective problem-solving. They are also able to experience and regulate emotions in a balanced and healthy manner."
        ),
    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutGlossaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding.backIcon.setOnClickListener {
            onBackPressed()
        }
        addTaskData()
    }

    private fun addTaskData() {
        val tasks = listOf(
            Task(
                "A",
                getString(R.string.adrenaline),
                getString(R.string.adrenaline_summary)
            ),
            Task(
                "A",
                getString(R.string.anxiety_attack),
                getString(R.string.anxiety_attack_summary)
            ),
            Task(
                "A",
                getString(R.string.automatic_thinking),
                getString(R.string.automatic_thinking_summary)
            ),

            Task(
                "B",
                getString(R.string.biased_thinking),
                getString(R.string.biased_thinking_summary)
            ),
            Task(
                "C",
                getString(R.string.cortisol),
                getString(R.string.cortisol_summary)
            ),
            Task(
                "C",
                getString(R.string.cognitive_distortion),
                getString(R.string.cognitive_distortion_summary),
            ),
            Task(
                "C",
                getString(R.string.competency),
                getString(R.string.competency_summary)
            ),
            Task(
                "C",
                getString(R.string.compulsive_behaviour),
                getString(R.string.compulsive_behaviour_summary),
            ),
            Task(
                "D",
                getString(R.string.Dependency),
                getString(R.string.Dependency_summary)
            ),
            Task(
                "G",
                getString(R.string.gaba),
                getString(R.string.gaba_summary)
            ),
            Task(
                "H",
                getString(R.string.hyperventilation),
                getString(R.string.hyper_summary),
            ),
            Task(
                "M",
                getString(R.string.mindful_breathing),
                getString(R.string.mindful_breathing_summary)
            ),
            Task(
                "O",
                getString(R.string.obsession),
                getString(R.string.obsession_summary)
            ),
            Task(
                "P",
                getString(R.string.panic_attack),
                getString(R.string.panic_attack_summary),
            ),
            Task(
                "P",
                getString(R.string.progressive_muscle_relaxation),
                getString(R.string.progressive_muscle_relaxation_summary),

                ),
            Task(
                "R",
                getString(R.string.resilience),
                getString(R.string.resilience_summary),
            ),
            Task(
                "R",
                getString(R.string.rigid_thinking),
                getString(R.string.rigid_thinking_summary),
            ),
            Task(
                "R",
                getString(R.string.rumination),
                getString(R.string.rumination_summary),
            ),
            Task(
                "S",
                getString(R.string.selfcompassion),
                getString(R.string.selfcompassion_summary),
            ),
            Task(
                "S",
                getString(R.string.serotonin),
                getString(R.string.serotonin_summary),
            ),
            Task(
                "W",
                getString(R.string.window_of_tolerance),
                getString(R.string.window_of_tolerance_summary),
            ),
        )
        taskWorkData.addAll(tasks)
        glossaryAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onStart() {
        binding.glossaryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.glossaryRecyclerView.adapter = glossaryAdapter
        glossaryAdapter.updateTasks(taskWorkData)
        addTaskData()
        super.onStart()
    }
}