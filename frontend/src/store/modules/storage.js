import axios from 'axios'
import {store} from "@/store/store"

const storage = {
    async fetch(orderState) {
        var setState = true;
        const arr = [];
        if(orderState == undefined || orderState === null) {
            setState == true;
        }
        else if(orderState != null || orderState != "") {
            setState = orderState;
        }

        await axios
            .get('/todos/' + setState)
            .then(res => {
                const jsonData = res.data;

                if(jsonData.length > 0){
                    for(let i = 0; i < jsonData.length; i++) {
                        arr.push(
                            jsonData[i]
                        )
                    }
                }
            })
        store.state.todoItems = arr;
    },
    fetchName() {
        if (localStorage.getItem("userName")) {
            const userName = localStorage.getItem("userName");
            return userName;
        }
    }
}


export default storage