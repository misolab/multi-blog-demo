const data = [
    {
      id: 1,
      title: "Java Blog Demo",
      subtitle: "This is developed by multi-archetype",
      writter: "misolab",
      updated: "2021-02-06T09:26:09.644+00:00",
      content: "<h2>Welcome Blog</h2><img class=\"img-responsive\" src=\"img/post-sample-image.jpg\">",
      backgroundImage: "images/post-bg.jpg",
    },
    {
      id: 33,
      title: "title1",
      subtitle: "subtitle1",
      writter: "misolab",
      updated: "2021-02-06T09:42:52.023+00:00",
      content: "<h2>Welcome Blog</h2><img class=\"img-responsive\" src=\"img/post-sample-image.jpg\">",
      backgroundImage: "images/post-bg.jpg",
    },
  ]
  
  module.exports = [
    {
      url: '/post',
      type: 'get',
      response: config => {
        return {
          code: 0,
          data: {
            list: {
              content: data
            },
          }
        }
      }
    }
  ]
  