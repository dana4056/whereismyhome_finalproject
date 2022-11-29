// 회원가입
function regist() {
  // 문서에서 id 로 input data 가져오기
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;
  let name = document.getElementById("name").value;
  let address = document.getElementById("address").value;
  let phone = document.getElementById("phone").value; 
  
  // 입력값 검증
  if (!id | !password | !name | !address | !phone) {
    alert("빈칸이 없도록 입력해주세요.");
    return;
  } else {
    // input data로 user 만들기
    const user = {
      id: id,
      password: password,
      name: name,
      address: address,
      phone: phone,
    };
      
      // 로컬스토리지에 있는 회원정보 가져오기    
      let userList = JSON.parse(localStorage.getItem("userList"));
      if (userList == null) {
        userList = [];
    }

    // user 객체 문자열로 바꿔서 로컬스토리지에 저장
    userList.push(user);
    localStorage.setItem("userList", JSON.stringify(userList));
    alert("사용자 등록 성공!");
    // 로그인 화면으로 돌아가기
    window.location.replace("login.html");
  }
}

// 비밀번호 길이 유효성 검사
function checkPassword(password) {

  let cnt = 0;
  for (let i = 0; i < password.length; i++) {
    let charCode = password.charCodeAt(i);
    if ((48 <= charCode && charCode <= 57) || (65 <= charCode && charCode <= 90) || (97 <= charCode && charCode <= 122)) {
      cnt++;
    }
  }

  if (cnt >= 6) return true;
  else return false;
}

// 로그인
function login() {
  // 문서에서 id로 input data 가져오기
  let id = document.getElementById("id").value;
  let password = document.getElementById("password").value;
  
  const loginUser = {
    id: id,
    password: password
  };

  // 로컬스토리지에 있는 회원정보 가져오기    
  let userList = JSON.parse(localStorage.getItem("userList"));
  if (userList == null) {
    userList = [];
  }

  for (let i = 0; i < userList.length; i++) {
    if (id === userList[i].id && password === userList[i].password) {
      localStorage.setItem("loginUser", JSON.stringify(loginUser));
      localStorage.setItem("loginStatus", 1); // 로그인 상태로 지정
      alert("로그인 성공 !");
      window.location.replace("index.html");
      break;
    }
  }

  let loginStatus = JSON.parse(localStorage.getItem("loginStatus")); // 
  if (loginStatus == 0) {
    alert("로그인 실패 !");
  }
}

// window.onload = function () {
//   let loginStatus = JSON.parse(localStorage.getItem("loginStatus")); // 
//   if (loginStatus == 1) { // 로그인된 상태
//     document.querySelector("#before-login").style.display = "none";
//     document.querySelector("#after-login").style.display = "";
//   } else {
//     document.querySelector("#before-login").style.display="";
//     document.querySelector("#after-login").style.display="none";
//   }
// };

// 로그아웃
function logout() {
  localStorage.setItem("loginStatus", 0); // 로그인 상태로 지정
  document.querySelector("#before-login").style.display="";
  document.querySelector("#after-login").style.display="none";
  localStorage.removeItem("loginUser");
  alert("로그아웃되었습니다.");
}


// 회원 정보 조회
function searchUserInfo() {
  let loginUser = JSON.parse(localStorage.getItem("loginUser")); // 현재 로그인한 사용자 정보
  let userList = JSON.parse(localStorage.getItem("userList")); // 사용자들의 정보 (전체)

  let id = document.querySelector("#id");
  let password = document.querySelector("#password");
  let name = document.querySelector("#name");
  let address = document.querySelector("#address");
  let phone = document.querySelector("#phone");

  for (let i = 0; i < userList.length; i++) {
    if (userList[i].id === loginUser.id) {

      id.value = userList[i].id;
      password.value = userList[i].password;
      name.value = userList[i].name;
      address.value = userList[i].address;
      phone.value = userList[i].phone;
      break;
    }
  }
}

// 회원 정보 수정
function updateUserInfo() {

  let loginUser = JSON.parse(localStorage.getItem("loginUser")); // 현재 로그인한 사용자 정보
  let userList = JSON.parse(localStorage.getItem("userList")); // 사용자들의 정보 (전체)

  let id = document.querySelector("#id").value;
  let password = document.querySelector("#password").value;
  let name = document.querySelector("#name").value;
  let address = document.querySelector("#address").value;
  let phone = document.querySelector("#phone").value;

  const user = {
    id: id,
    password: password,
    name: name,
    address: address,
    phone: phone,
  };

  for (let i = 0; i < userList.length; i++) {
    if (userList[i].id === loginUser.id) {

      userList[i].id = id;
      userList[i].password = password;
      userList[i].name = name;
      userList[i].address = address;
      userList[i].phone = phone;
      localStorage.setItem("userList", JSON.stringify(userList));

      alert("회원 정보 수정이 완료되었습니다.");
      break;
    }
  }
}

function deleteUserInfo() {

  let idx = 0;
  let loginUser = JSON.parse(localStorage.getItem("loginUser")); // 현재 로그인한 사용자 정보
  let userList = JSON.parse(localStorage.getItem("userList")); // 사용자들의 정보 (전체)

  let updateUserList = [];

  for (let i = 0; i < userList.length; i++) {
    if (userList[i].id === loginUser.id) continue;

    updateUserList[idx++] = userList[i];
  }

  localStorage.setItem("userList", JSON.stringify(updateUserList));
  alert("탈퇴되었습니다.");

  // 로그아웃도 해주기
  logout();
  window.location.replace("index.html");
}

function findPassword() {
  
  let flag = 0;
  let userList = JSON.parse(localStorage.getItem("userList")); // 사용자들의 정보 (전체)

  // prompt 로 아이디 입력 받기
  let inputId = prompt("아이디를 입력하세요");

  for (let i = 0; i < userList.length; i++) {
    if (userList[i].id === inputId) {
      flag = 1;
      // alert으로 비밀 번호 보여주기
      alert(userList[i].password);
    }
  }

  if (flag == 0) {
    alert("비밀번호를 찾을 수 없습니다.");
  }
}