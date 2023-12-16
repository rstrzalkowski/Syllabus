export interface RealisationInfoPage {
  content: RealisationInfo[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface RealisationInfo {
  id: string
  subjectName: string
  teacherId: string
  year: number
  teacherFirstName: string
  teacherLastName: string
  schoolClassName: string
  subjectAbbreviation: string
  imageUrl: string
}
