# Android-HiJoe
This project is about detecting disease in vegetable plant using Machine Learning. Farthermore, this app will serve data about detected disease description as well as its cure.

<a href="https://drive.google.com/file/d/1Sp_osVybWaueGMIom-q9MLCS06EgYsRn/view?usp=sharing"><img src="https://img.shields.io/badge/DOWNLOAD%20APK-v1.0-brightgreen" alt="Hi-Joe"/></a> <br/>
<a href="https://www.python.org/"><img src="https://img.shields.io/badge/python-v3.9.4-yellow" alt="Python"/></a>
<a href="https://www.tensorflow.org/"><img src="https://img.shields.io/badge/tensorflow-v2.5-orange" alt="TensorFlow"/></a>
<a href="https://kotlinlang.org/"><img src="https://img.shields.io/badge/kotlin-v1.4.21-blue" alt="Kotlin"/></a>
<a href="https://gradle.org/"><img src="https://img.shields.io/badge/gradle-v6.5-green" alt="Gradle"/></a>
<a href="https://developer.android.com/studio/"><img src="https://img.shields.io/badge/android%20studio-v4.1.2-blue" alt="Android Studio"></a>

### Limitations
* Only use Potato, Tomato, and Pepper Bell plant
* Big application size because of Tensorflow Lite


## DOCUMENTATION
### Machine Learning
1. Download dataset in [Plant Village Disease](https://www.kaggle.com/emmarex/plantdisease)
2. Extract the dataset
3. Create python notebook to process the dataset
4. Load the dataset and devide them into training set and test set
5. In this project we use CNN Model Baseline. So create some neural network layer before training the data
6. Wait and check the accuracy in every epoch
7. Save the data in Keras Model (*.h5)
8. Convert the Keras Model into Tensorflow lite model (*.tflite)

[Sample Code](https://drive.google.com/file/d/1hdLJeEZV6HEeyOUUYhq30znCbmRMz9Jf/view?usp=sharing)

### Android
1. Create new android project
2. Import the tflite file by:
    * File
    * New
    * Other
    * Tensorflow Lite Model
    * Choose your tflite file
    * Check all of the suggestion
3. Implement the sample code within the tflite model in Android Studio
4. Setup for http request (This project use Retrofit2)
5. Send the id which is earned from ML process to API with GET method

### Cloud Computing
1. Activate firestore in GCP
2. Add new collection and data within
3. Create and API handler
4. Deploy the API handler to GCP
5. Configure the  API with the firestore


## WANT TO KNOW THE CODE?
1. Clone this repository using git command or git GUI
2. Anyway, You can also download this project

# HAVE FUN :green_heart:
