import getDate from "./../../assets/common/getDate"
import axios from 'axios'
import storage from "@/store/modules/storage"

const addOneItem = async (state, todoItem) => {
    var jsonValue = {
        itme: todoItem,
        date: `${getDate().date} ${getDate().week}`,
        time: getDate().time,
        completed: false,
    }

    await axios
        .post('/todos/save', JSON.stringify(jsonValue))
        .then(res => {
            if(res.data == "ok"){
                storage.fetch(state.todoOldestOrder);
            }else {
                alert("등록 실패");
            }
        })
}

const removeOneItem = (state, payload) => {
    axios
        .put('/todos/delete/' + payload.todoItem.id)
        .then(res => {
            if(res.data == "ok") {
                storage.fetch(state.todoOldestOrder);
            }else{
                alert("삭제 실패");
            }
        })
}

const toggleOneItem = (state, payload) => {
    var jsonValue = {
        id: payload.todoItem,
        completed: !payload.todoItem.completed
    }

    axios
        .put('/todos' + payload.todoItem.id, JSON.stringify(jsonValue))
        .then(res => {
            if(res.data == "ok"){
                storage.fetch(state.todoOldestOrder);
            }else{
                alert("업데이트 실패");
            }
        })
}

const clearAllItem = (state) => {
    var todoItems = state.todoItems;
    if(todoItems.length > 0){
        axios
            .put('/todos/clear')
            .then(res => {
                if(res.data == "ok"){
                    storage.fetch(state.todoOldestOrder);
                }else{
                    alert("클리어 실패");
                }
            })
    }
}

const sortTodoLatest = (state) => {
    state.todoOldestOrder = false;
    storage.fetch(state.todoOldestOrder);
}

const sortTodoOldest = (state) => {
    state.todoOldestOrder = true;
    storage.fetch(state.todoOldestOrder);
}

const setUserName = (state, userName) => {
    localStorage.setItem("userName", userName);
    state.userName = userName;
}

export { addOneItem, removeOneItem, toggleOneItem, clearAllItem, sortTodoLatest, sortTodoOldest, setUserName }