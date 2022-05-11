// (1) 회원정보 수정


function update(userId, event) {
    event.preventDefault();//폼테그 액션 막기


    let data = $("#profileUpdate").serialize();

    console.log(data);



    $.ajax({
        type: "put",
        url:`/api/user/${userId}`,
        data:data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"

    }).done(res => {//http 상태 코드 200
        console.log("update성공",res)
        location.href = `/user/${userId}`;

    }).fail(error => {// http 상태코드 200아닐때

        if (error.data == null) {
            alert(error.responseJSON.message);
        }else {
            alert(JSON.stringify(error.responseJSON.data));
            //stringify 제이슨을 스트링으로
            // console.log("update실패", error.responseJSON.data);
        }
    });



}


// function update(userId, event) {
//     console.log(event);
//
//
// }

// function update() {
//     alert("update")
//
//
// }