package com.basic.childrenbook.data.repository

import com.basic.childrenbook.data.remote.BookApi
import com.basic.childrenbook.domain.Book
import com.basic.childrenbook.domain.toDomain
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BookApi) {

    suspend fun searchBooks(query: String): Result<List<Book>> = runCatching {
        getCuratedBooks(query)
    }

    private fun getCuratedBooks(query: String): List<Book> {
        val q = query.lowercase()
        return when {
            q.contains("0") || q.contains("high contrast") || q.contains("black") -> listOf(
                Book(
                    "c1",
                    "Black and White",
                    "Tana Hoban",
                    "High-contrast fold-out accordion board book designed for newborn visual tracking and tummy time.",
                    null,
                    "High Contrast",
                    "★ 4.9 (9.3k)",
                    "$7.19",
                    "https://www.amazon.com/s?k=Black+and+White+Tana+Hoban"
                ),
                Book(
                    "c2",
                    "Look, Look!",
                    "Peter Linenthal",
                    "Striking black-and-white cutouts with red accents crafted to catch newborn eyes and stimulate vision.",
                    null,
                    "High Contrast",
                    "★ 4.8 (11.2k)",
                    "$7.99",
                    "https://www.amazon.com/s?k=Look+Look+Peter+Linenthal"
                ),
                Book(
                    "c3",
                    "Hello, Baby Animals",
                    "duopress labs",
                    "High-contrast geometric animal shapes designed with early childhood visual experts to practice eye tracking.",
                    null,
                    "Visual Stimulation",
                    "★ 4.8 (6.8k)",
                    "$8.95",
                    "https://www.amazon.com/s?k=Hello+Baby+Animals+duopress+labs"
                ),
                Book(
                    "c4",
                    "Baby Animals Black and White",
                    "Phyllis Limbacher Tildes",
                    "Detailed, realistic black-and-white wildlife illustrations introducing organic contours to infants.",
                    null,
                    "High Contrast",
                    "★ 4.7 (3.5k)",
                    "$6.99",
                    "https://www.amazon.com/s?k=Baby+Animals+Black+and+White+Phyllis+Limbacher+Tildes"
                ),
                Book(
                    "c5",
                    "Tummy Time!",
                    "Mama Makes Books",
                    "Double-sided fold-out floor book featuring bold graphic patterns and a baby-safe mirror for motor play.",
                    null,
                    "Tummy Time",
                    "★ 4.7 (2.1k)",
                    "$8.99",
                    "https://www.amazon.com/s?k=Tummy+Time+Mama+Makes+Books"
                ),
                Book(
                    "c6",
                    "Ten Little Fingers and Ten Little Toes",
                    "Mem Fox",
                    "Gentle, predictable meter and repetitive rhythm to reinforce early phonetic patterns and touch bonding.",
                    null,
                    "Rhyme & Rhythm",
                    "★ 4.9 (14.5k)",
                    "$8.99",
                    "https://www.amazon.com/s?k=Ten+Little+Fingers+and+Ten+Little+Toes+Mem+Fox"
                ),
                Book(
                    "c7",
                    "Goodnight Moon",
                    "Margaret Wise Brown",
                    "Calm, lulling rhythmic cadence that mimics a gentle heartbeat to build soothing sleep associations.",
                    null,
                    "Bedtime",
                    "★ 4.9 (35.2k)",
                    "$5.36",
                    "https://www.amazon.com/s?k=Goodnight+Moon+Margaret+Wise+Brown"
                ),
                Book(
                    "c8",
                    "On the Night You Were Born",
                    "Nancy Tillman",
                    "Warm, expressive poetic read-aloud designed to foster emotional inflection and parent-infant bonding.",
                    null,
                    "Emotional Bonding",
                    "★ 4.9 (24.1k)",
                    "$7.98",
                    "https://www.amazon.com/s?k=On+the+Night+You+Were+Born+Nancy+Tillman"
                ),
                Book(
                    "c9",
                    "The Going to Bed Book",
                    "Sandra Boynton",
                    "Playful, bouncy rhythm and melodic phrasing ideal for infant-directed speech and early vowel mapping.",
                    null,
                    "Bedtime",
                    "★ 4.8 (31.7k)",
                    "$5.98",
                    "https://www.amazon.com/s?k=The+Going+to+Bed+Book+Sandra+Boynton"
                ),
                Book(
                    "c10",
                    "The Real Mother Goose",
                    "Blanche Fisher Wright",
                    "Timeless nursery rhymes whose natural acoustic bounce stimulates neural pathways and phonemic awareness.",
                    null,
                    "Nursery Rhymes",
                    "★ 4.8 (7.8k)",
                    "$9.99",
                    "https://www.amazon.com/s?k=The+Real+Mother+Goose"
                )
            )

            q.contains("3") || q.contains("cloth") || q.contains("crinkle") -> listOf(
                Book("d1", "Where's the Ladybug?", "Ingela P. Arrhenius", "Soft felt flap book with bright artwork and a mirror, perfect for developing fine-motor grasping.", null, "Felt Flaps", "★ 4.8 (8.1k)", "$8.99", "https://www.amazon.com/s?k=Wheres+the+Ladybug+Ingela+P+Arrhenius"),
                Book("d2", "Making Faces: A First Book of Emotions", "Abrams Appleseed", "Large photos of real baby expressions with an infant-safe mirror to foster social-emotional learning.", null, "Real Faces", "★ 4.8 (7.3k)", "$7.99", "https://www.amazon.com/s?k=Making+Faces+A+First+Book+of+Emotions"),
                Book("d3", "Peek-a-Who?", "Nina Laden", "Rhythmic peek-a-boo board book with die-cut preview windows and an engaging mirror ending.", null, "Interactive", "★ 4.8 (28.4k)", "$5.84", "https://www.amazon.com/s?k=Peek+a+Who+Nina+Laden"),
                Book("d4", "Indestructibles: Baby Peekaboo", "Amy Pixton", "100% chew-proof, tear-proof, washable book built for the intense mouthing and crinkling stage.", null, "Chew Proof", "★ 4.9 (16.2k)", "$5.95", "https://www.amazon.com/s?k=Indestructibles+Baby+Peekaboo"),
                Book("d5", "Pat the Bunny", "Dorothy Kunhardt", "Classic multi-sensory touch-and-feel book featuring soft bunny fur, peek-a-boo fabric, and varied textures.", null, "Touch & Feel", "★ 4.8 (22.5k)", "$6.98", "https://www.amazon.com/s?k=Pat+the+Bunny+Dorothy+Kunhardt"),
                Book("d6", "Brown Bear, Brown Bear, What Do You See?", "Bill Martin Jr.", "Rich, saturated color collages and rhythmic cadence tailored for emerging infant color vision.", null, "Color & Rhythm", "★ 4.9 (58.3k)", "$5.36", "https://www.amazon.com/s?k=Brown+Bear+Brown+Bear+What+Do+You+See"),
                Book("d7", "Never Touch a Dinosaur!", "Rosie Greening", "Bumpy silicone ridges and bright contrasting colors providing rich tactile feedback for little fingers.", null, "Sensory Silicone", "★ 4.8 (19.4k)", "$8.99", "https://www.amazon.com/s?k=Never+Touch+a+Dinosaur"),
                Book("d8", "Baby Faces", "Margaret Miller", "Close-up portraits of smiling, crying, and laughing infants that capture natural baby curiosity.", null, "Real Faces", "★ 4.7 (4.1k)", "$6.99", "https://www.amazon.com/s?k=Baby+Faces+Margaret+Miller"),
                Book("d9", "Where Is Baby's Belly Button?", "Karen Katz", "Playful interactive discovery book that teaches foundational body part awareness and early vocabulary.", null, "Lift-the-Flap", "★ 4.8 (21.7k)", "$5.99", "https://www.amazon.com/s?k=Where+Is+Babys+Belly+Button+Karen+Katz"),
                Book("d10", "Fuzzy Bee and Friends", "Roger Priddy", "Crinkle fabric cloth book with ribbon tags that provides auditory and tactile feedback during grasping.", null, "Crinkle Cloth", "★ 4.7 (5.6k)", "$7.19", "https://www.amazon.com/s?k=Fuzzy+Bee+and+Friends+Roger+Priddy")
            )

            q.contains("6") || q.contains("board") || q.contains("flap") -> listOf(
                Book("e1", "Dear Zoo", "Rod Campbell", "Classic lift-the-flap story testing object permanence and animal recognition with chunky flaps.", null, "Lift-the-Flap", "★ 4.8 (32.1k)", "$6.99", "https://www.amazon.com/s?k=Dear+Zoo+Rod+Campbell"),
                Book("e2", "Moo, Baa, La La La!", "Sandra Boynton", "Humorous animal sound rhyming board book favored by speech therapists for sound imitation.", null, "Early Speech", "★ 4.9 (42.5k)", "$5.97", "https://www.amazon.com/s?k=Moo+Baa+La+La+La+Sandra+Boynton"),
                Book("e3", "First 100 Words", "Roger Priddy", "Padded photographic picture book designed to encourage pointing and everyday object labeling.", null, "Vocabulary", "★ 4.8 (85.2k)", "$5.99", "https://www.amazon.com/s?k=First+100+Words+Roger+Priddy"),
                Book("e4", "The Very Hungry Caterpillar", "Eric Carle", "Chunky board book with die-cut finger holes ideal for developing pincer grasp exploration.", null, "Fine Motor", "★ 4.9 (67.4k)", "$7.19", "https://www.amazon.com/s?k=The+Very+Hungry+Caterpillar+board+book"),
                Book("e5", "Baby Beluga", "Raffi", "Rhythmic sing-along board book that bridges musical melody with early phonological memory.", null, "Songs & Melody", "★ 4.9 (11.8k)", "$7.99", "https://www.amazon.com/s?k=Baby+Beluga+Raffi"),
                Book("e6", "Little Green Frog", "Ginger Swift", "Ultra-sturdy chunky lift-a-flap board book built to withstand rough pulling and grasping.", null, "Chunky Flaps", "★ 4.8 (9.5k)", "$7.98", "https://www.amazon.com/s?k=Little+Green+Frog+Ginger+Swift"),
                Book("e7", "Bizzy Bear: Farmyard Fun", "Benji Davies", "Engaging push, pull, and slide mechanisms that teach cause-and-effect and motor dexterity.", null, "Sliders", "★ 4.8 (6.7k)", "$8.99", "https://www.amazon.com/s?k=Bizzy+Bear+Farmyard+Fun"),
                Book("e8", "Clap Hands", "Helen Oxenbury", "Action-packed rhythm book encouraging babies to clap, wave, and mirror physical social gestures.", null, "Gestures", "★ 4.7 (3.2k)", "$7.99", "https://www.amazon.com/s?k=Clap+Hands+Helen+Oxenbury"),
                Book("e9", "In My Tree", "Sara Gillingham", "Built-in plush owl finger puppet that holds baby gaze and develops joint attention.", null, "Finger Puppet", "★ 4.8 (4.6k)", "$8.99", "https://www.amazon.com/s?k=In+My+Tree+Sara+Gillingham"),
                Book("e10", "Say Hello to the Baby Animals!", "Ian Whybrow", "Lively animal noises and interactive rhyming lines that build early conversational turn-taking.", null, "Animal Sounds", "★ 4.7 (2.8k)", "$7.49", "https://www.amazon.com/s?k=Say+Hello+to+the+Baby+Animals+Ian+Whybrow")
            )

            else -> listOf(
                Book("f1", "Little Blue Truck", "Alice Schertle", "Beloved rhythmic vehicle and farm animal adventure teaching kindness, friendship, and teamwork.", null, "Sound & Social", "★ 4.9 (48.3k)", "$7.48", "https://www.amazon.com/s?k=Little+Blue+Truck+Alice+Schertle"),
                Book("f2", "Where Is the Green Sheep?", "Mem Fox", "Playful rhyming search-and-find picture book introducing colors, opposites, and descriptors.", null, "Concepts", "★ 4.8 (12.6k)", "$7.99", "https://www.amazon.com/s?k=Where+Is+the+Green+Sheep+Mem+Fox"),
                Book("f3", "From Head to Toe", "Eric Carle", "Energetic gross-motor movement book that challenges toddlers to imitate animal physical actions.", null, "Movement", "★ 4.9 (18.1k)", "$7.19", "https://www.amazon.com/s?k=From+Head+to+Toe+Eric+Carle"),
                Book("f4", "Where's Spot?", "Eric Hill", "Foundational lift-the-flap classic that introduces toddlers to spatial prepositions and animal discovery.", null, "Lift-the-Flap", "★ 4.8 (14.2k)", "$6.99", "https://www.amazon.com/s?k=Wheres+Spot+Eric+Hill"),
                Book("f5", "Press Here", "Hervé Tullet", "Imaginative tactile book where instructions to press, shake, and tilt make the visual world react.", null, "Interactive", "★ 4.8 (17.5k)", "$8.99", "https://www.amazon.com/s?k=Press+Here+Herve+Tullet"),
                Book("f6", "Good Night, Gorilla", "Peggy Rathmann", "Delightfully illustrated bedtime adventure that lets toddlers decipher visual storylines and humor.", null, "Visual Story", "★ 4.8 (21.3k)", "$6.99", "https://www.amazon.com/s?k=Good+Night+Gorilla+Peggy+Rathmann"),
                Book("f7", "Chicka Chicka Boom Boom", "Bill Martin Jr.", "Lively chant-like alphabet rhyming book with an irresistible jazzy cadence toddlers love to recite.", null, "Alphabet & Rhythm", "★ 4.9 (53.8k)", "$6.79", "https://www.amazon.com/s?k=Chicka+Chicka+Boom+Boom"),
                Book("f8", "Barnyard Dance!", "Sandra Boynton", "Catchy square-dance rhythm that gets active toddlers jumping, twirling, and clapping along.", null, "Active Dance", "★ 4.9 (29.7k)", "$5.97", "https://www.amazon.com/s?k=Barnyard+Dance+Sandra+Boynton"),
                Book("f9", "Llama Llama Red Pajama", "Anna Dewdney", "Comforting rhyming tale that helps toddlers process bedtime anxiety and emotional separation.", null, "Emotions", "★ 4.9 (38.2k)", "$7.99", "https://www.amazon.com/s?k=Llama+Llama+Red+Pajama+Anna+Dewdney"),
                Book("f10", "Potty", "Leslie Patricelli", "Gentle, humorous introduction to potty awareness and bodily autonomy told from a toddler's perspective.", null, "Milestones", "★ 4.8 (34.1k)", "$6.99", "https://www.amazon.com/s?k=Potty+Leslie+Patricelli")
            )
        }
    }
}
