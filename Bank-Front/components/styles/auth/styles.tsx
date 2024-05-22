
import { StyleSheet, Animated } from "react-native";
const animated = new Animated.Value(1);
export const styles = StyleSheet.create({
    container: {
        flex: 1,
        // alignItems: 'center',
        justifyContent: 'center',
        alignSelf: 'stretch',
        padding: 10,
    },
    title: {
        fontSize: 30,
        fontWeight: 'bold',
        marginBottom: 15,
    },
    inputLable: {
        padding: 15,
        lable: {
            marginBottom: 10,
            fontWeight: 'bold',
        }
    }, 
    error: {
        color: 'red'
    },
    box: {
        borderWidth: 1,
        padding: 25,
    },
    input: {
        borderWidth: 1,
        paddingVertical: 13,
        paddingHorizontal: 15,
    },

    buttonDiv: {
        alignItems: 'center'
    },
    button: {
        opacity: animated,
        backgroundColor: "rgb(11, 87, 208)",
        paddingVertical: 10,
        paddingHorizontal: 20,
        marginTop: 15,
        text: {

            color: "rgb(255,255,255)",
        }
    },
    separator: {
        marginVertical: 30,
        height: 1,
        width: '80%',
    },
});
export const fadeOut = () => {
    Animated.timing(animated, {
        toValue: 1,
        duration: 200,
        useNativeDriver: true,
    }).start();
};
export const fadeIn = () => {
    Animated.timing(animated, {
        toValue: 0.4,
        duration: 100,
        useNativeDriver: true,
    }).start();
};