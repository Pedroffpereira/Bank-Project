
import { StyleSheet, Animated } from "react-native";
const animated = new Animated.Value(1);
export const styles = StyleSheet.create({
    container: {
        flex: 1,
        // alignItems: 'center',
        alignSelf: 'stretch',
        padding: 10,
        backgroundColor: '#00559a'
    },
    title: {
        marginBottom: 20,
        color: '#fff',
        fontSize: 20
    },
    inputLable: {
        padding: 15,
        lable: {
            marginBottom: 10,
            fontWeight: 'bold',
        }
    },
    box: {
        borderWidth: 1,
        padding: 25,
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
            textAlign: 'center',
            color: "rgb(255,255,255)",
        }
    },
    separator: {
        marginVertical: 30,
        height: 1,
        width: '80%',
    },
    text: {
        color: '#fff'
    },
    input: {
        backgroundColor: '#fff',
        borderWidth: 1,
        borderRadius: 50,
        padding: 10,
        marginTop: 10,
    },
    error: {
        color: 'red'
    },
    center: {
        margin: 'auto',
    },

    space: {
        marginVertical: 15,
    }
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