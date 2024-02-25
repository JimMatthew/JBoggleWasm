function fetchData() {
  // Define the URL for the GET request
  const url = 'https://github.com/JimMatthew/JBoggleAndroid/blob/5b6880d851b43cbab932706c38b9f02b73700533/app/enable1.txt';

  // Make the GET request using Fetch
  return fetch(url)
    .then(response => {
      // Check if the response is OK (status code 200)
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // Parse the response as plain text
      return response.text();
    })
    .catch(error => {
      // Handle any errors that occurred during the fetch
      console.error('There was a problem with the fetch operation:', error);
      // Return an empty string in case of an error
      return '';
    });
}