function showPost(){
    let url = '/api/v1/pool'
    let getPost = fetch(url).then((res) => {
        return res.json();
    })
    .then((data) => {
        let posts = data;
        console.log("posts : ", posts)

        const postList = data.pools;
        for(var i=0; i<postList.length; i++){
            const name = document.createElement("form");
            name.setAttribute("charset", "UTF-8");
            name.setAttribute("method", "Get");
            name.setAttribute("action", "/api/v1/render/"+data.pools[i].depth);

//            name.textContent = data.pools[i].depth;
//            name.id = data.pools[i].depth;
//            name.onclick = function(){renderAuctionPage(this.id)};
            const postInfo = document.getElementById("postInfo");
            postInfo.appendChild(name);
        }

    })
}
function renderAuctionPage(id){
    console.log(typeof id);
    let url = '/api/v1/render/'+Number(id);
    console.log(url);
    fetch(url);
}