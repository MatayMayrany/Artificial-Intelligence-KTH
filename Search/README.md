# Search Assignment - DD3820

## Description 

### Gameplay specifics:
The boats can move horizontally and have a fishing line attached. At the end of the fishing line is a hook which can be moved vertically. Consequently, the possible actions for each player are LEFT, RIGHT, UP, DOWN and STAY. Note that we consider a  2D scenario, and therefore the boats cannot cross each other. The players take turns while the fish moves at each time step.

The fish can move in the 9 basic directions (UP, DOWN, LEFT, RIGHT, UP-LEFT, UP-RIGHT, DOWN-LEFT, DOWN-RIGHT and STAY). A fish is caught once its position coincides with the hook’s position. Note that some fish are more valuable than others, resulting in different score points per fish. A penalty (negative points) is given for types of fish that should not be caught. The correspondence between the type of fish and number of points is known at the beginning of the game.

The opponent you play against is a minimax opponent capable of reaching large depths, which in the testing scenarios will result in a perfect minimax. But don’t worry! In all scenarios you will be put in a position in which if you perform the right moves, you are guaranteed to win. Therefore, the deciding factor for your victory will be the depth that your algorithm is capable of reaching within the predefined deadline (i.e., how efficient is your minimax implementation) and how well your heuristic characterizes the value of each state.

For more details, please refer to the code inside the provided skeletons.


### Provided code:
The code skeleton will be provided for you in Python. You should modify the player file player.py and you may also create new files. The files included in the skeleton, other than player.py, may be modified locally but keep in mind that they will be overwritten on Kattis. You only need to upload the player file, and of course any additional python files you have created.

The Python code skeleton is available in the attachments. You can check the installation instructions in the text file README.md.


### Input:
Your interface with the judge is the player file player.py. When it is the your player’s turn, the minimax implementation in player.py will be called determining what the player should do.

The player should spend less than 75 ms on each time step before returning a guess, otherwise the evaluation of the code will stop with a run time error.

Some sample inputs (scenarios) are provided in the code skeleton (the JSON files given in the observations folder). In particular, the input given by test_0.json loads a scenario intended for you to play around with and see how your implementation works. However, the scenario is randomly generated and does not represent how the testing scenarios look like! To get a feeling how the testing scenarios look like (used in Kattis to evaluate your performance), we also include three such scenarios given by test_1.json, test_2.json and test_3.json). These three scenarios have varying difficulty due to the varying depth one needs to have to win. Which scenario is loaded is determined by the file settings.yml.


### Output:
The skeleton handles all the output for you. Avoid using stdin and stdout. (Use stderr for debugging.)
